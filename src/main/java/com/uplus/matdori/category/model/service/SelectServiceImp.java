package com.uplus.matdori.category.model.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uplus.matdori.category.model.dao.CategoryDAO;
import com.uplus.matdori.category.model.dao.HistoryDAO;
import com.uplus.matdori.category.model.dao.UserDAO;

import com.uplus.matdori.category.model.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Random;


//뽑기 관련 Service 구현
@Slf4j
@Service
public class SelectServiceImp implements SelectService {

    //로그 찍어보자....
    private static final Logger logger = LoggerFactory.getLogger(SelectServiceImp.class);

    private final CategoryDAO categoryDAO;
    private final UserDAO userDAO;
    private final HistoryDAO historyDAO;
    private final Random random = new Random();

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper(); // JSON 변환을 위한 ObjectMapper

    //네이버 지도 API 설정
    private static final String NAVER_CLIENT_ID = "RvaDxZwNtw9yXP8tGvAQ"; // 네이버 Client ID 입력
    private static final String NAVER_CLIENT_SECRET = "SAmVlYp2QJ"; // 네이버 Client Secret 입력
    private static final String NAVER_LOCAL_SEARCH_API = "https://openapi.naver.com/v1/search/local.json";

    //네이버 Geocoding 등... 다른 api 관련 정보
    private static final String CLIENT_ID_2 = "02ykeyehkx"; // 발급받은 Client ID
    private static final String CLIENT_SECRET_2 = "mO5YsavNQw8Ni7uEHTnkS6bJ6PdwphZ8SxWeEsOP"; // 발급받은 Client Secret
    private static final String REVERSE_GEOCODING_URL_2 = "https://naveropenapi.apigw.ntruss.com/map-reversegeocode/v2/gc";

    //초기에 DAO와 userDAO 초기화하는 생성자
    @Autowired
    public SelectServiceImp(CategoryDAO categoryDAO, UserDAO userDAO, HistoryDAO historyDAO) {
        this.categoryDAO = categoryDAO;
        this.userDAO = userDAO;
        this.historyDAO = historyDAO;
    }

    //"랜덤한" 카테고리 ID 선택 후, 이를 이용해서 검색 정보 불러서 Client에 넘겨주는 메소드
    //네이버 지역 검색 API를 활용
    @Override
    public ResponseEntity<ApiResponse<NaverLocalResponseDTO>> getRandomCategory(double latitude, double longitude, String selectCategoryName) {
        try {
            if (selectCategoryName == null) {
                int randomId = random.nextInt(15) + 1; // 1~15 랜덤 숫자 생성
                String categoryName = categoryDAO.search(randomId); // 랜덤 ID에 해당하는 카테고리명 조회 

                // 카테고리명이 비어있다면 404 반환
                if (categoryName == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(ApiResponse.error("카테고리를 찾을 수 없습니다."));
                }
                //testSearchWithLocation() 메소드를 이용해서 위치+키워드 기반으로 상위 5개 음식점 정보를 돌려준다
                return ResponseEntity.ok(ApiResponse.success(testSearchWithLocation(categoryName, latitude, longitude)));
            } else {
                // 카테고리 존재 여부 확인
                Integer categoryId = categoryDAO.checkCategoryName(selectCategoryName);

                // 카테고리가 존재하지 않으면 400 반환
                if (categoryId == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(ApiResponse.error("존재하지 않는 카테고리입니다."));
                }

                // 검색 결과 반환
                return ResponseEntity.ok(ApiResponse.success(testSearchWithLocation(selectCategoryName, latitude, longitude)));
            }
        } catch (Exception e) {
	         throw new RuntimeException(e);
	      }
    }


    @Override
    public String getPreferredCategory(String userId) {
        UserDTO user = userDAO.getUserById(userId);
        List<Integer> categoryVisits = user.getCategoryVisits(); // List<Integer>로 변경

        int maxVisits = 0, bestCategory = 1;
        for (int i = 0; i < categoryVisits.size(); i++) { // .length → .size() 사용
            if (categoryVisits.get(i) > maxVisits) { // 배열 인덱싱 → get(i) 사용
                maxVisits = categoryVisits.get(i);
                bestCategory = i + 1;
            }
        }
        return categoryDAO.search(bestCategory);
    }

    public String getRegionName(double latitude, double longitude) {

        String coords = longitude+","+latitude;
        String url = REVERSE_GEOCODING_URL_2 + "?coords=" + coords + "&output=json&orders=admcode";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-APIGW-API-KEY-ID", CLIENT_ID_2);
        headers.set("X-NCP-APIGW-API-KEY", CLIENT_SECRET_2);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(response.getBody());
                JsonNode results = root.path("results");

                if (results.isArray() && results.size() > 0) {
                    JsonNode firstResult = results.get(0);
                    JsonNode region = firstResult.path("region");
                    JsonNode land = firstResult.path("land");

                    String area1 = region.path("area1").path("name").asText();
                    String area2 = region.path("area2").path("name").asText();
                    String area3 = region.path("area3").path("name").asText();

                    return area1 + " " + area2 + " " + area3 + " "; // "서울특별시 관악구 중앙동" 형식
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //위치 정보와 함께 키워드를 생성했다고 가정하고, 위치 기반으로 네이버 지역 검색 API를 호출하는 메소드 testSearchWithLocation()
    public NaverLocalResponseDTO testSearchWithLocation(String categoryName, double latitude, double longitude) {

        log.info("latitude: {}, longitude: {}", latitude, longitude);

        //1. 위도, 경도 값을 받아와서, 지역명 정보를 받아온다
        String result = getRegionName(latitude, longitude);

        log.info("result: {}", result);

        //result 값이 null이면(네이버 지도 api에서 "지역명" 정보를 못 불러왔을 경우)
        if(result == null) {
            throw new RuntimeException();
        }

        //2. 위치를 기반으로 키워드 생성 (ex. "선릉역 ${패스트푸드})
        String keyword = result + " " + categoryName;

        //3. 네이버 지역 검색 API 호출
        return searchPlaces(keyword);
    }

    //네이버 지역 검색 API를 호출하는 메소드 searchPlaces()
    private NaverLocalResponseDTO searchPlaces(String query) {
        //네이버 API 요청을 위한 HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", NAVER_CLIENT_ID);
        headers.set("X-Naver-Client-Secret", NAVER_CLIENT_SECRET);

        //API 요청 URL 구성 (최대 5개 결과 요청)
        String url = NAVER_LOCAL_SEARCH_API + "?query=" + query + "&display=5&start=1&sort=random";
        logger.info("네이버 지역 검색 API 요청 url: {}", url);

        //HTTP 요청 실행
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<NaverLocalResponseDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                NaverLocalResponseDTO.class //JSON을 바로 DTO로 변환
        );

        //API 응답 로그 출력
        //JSON으로 변환하여 로그 출력
        try {
            String jsonResponse = objectMapper.writeValueAsString(response.getBody());
            logger.info("네이버 지역 검색 API 응답: {}", jsonResponse);
        } catch (Exception e) {
            logger.error("JSON 변환 오류", e);
        }

        return response.getBody(); //JSON을 그대로 반환
    }

    //회원의 특정 카테고리 방문 횟수 증가시키고, 방문한 식당 정보를 히스토리에 기록하는 confirmVisitAndUpdateCategory()
    public ResponseEntity<ApiResponse<Object>> confirmVisitAndUpdateCategory(String userId, HistoryDTO history) {
        //1. 현재 회원 정보를 가져오기
        UserDTO user = userDAO.getUserById(userId);
        if(user == null) {
            //실패하면.. BAD_REQUEST 선언
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("유저를 찾을 수 없어요"));
        }

        //2. 특정 카테고리 방문 횟수 증가 (DTO 내부에서)
        int categoryId = history.getCategory_id2();
        user.incrementCategoryVisitCount(categoryId);

        //3. 변경된 정보를 DB에 반영
        userDAO.incrementCategoryVisitCount(userId, categoryId);

        //4. 방문한 식당 정보를 "방문한_식당_히스토리" 테이블에 삽입
        history.setUser_id2(userId); //JSON의 "user_id" 값을 VisitHistoryDTO의 "user_id2"로 매핑
        historyDAO.insertVisitHistory(history);

        //success일 경우에는 Map.of() 넘겨서.. content는 빈 객체를 넘김
        return ResponseEntity.ok(ApiResponse.success(Map.of()));
    }
}
