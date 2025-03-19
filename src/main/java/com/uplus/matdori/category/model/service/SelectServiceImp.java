package com.uplus.matdori.category.model.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uplus.matdori.category.model.dao.CategoryDAO;
import com.uplus.matdori.category.model.dao.HistoryDAO;
import com.uplus.matdori.category.model.dao.UserDAO;
import com.uplus.matdori.category.model.dto.CategoryDTO;
import com.uplus.matdori.category.model.dto.HistoryDTO;
import com.uplus.matdori.category.model.dto.NaverLocalResponseDTO;
import com.uplus.matdori.category.model.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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
    public NaverLocalResponseDTO getRandomCategory() {
        int randomId = random.nextInt(15) + 1; // 1~15 랜덤 숫자 생성
        String categoryName = categoryDAO.search(randomId); //랜덤 ID에 해당하는 카테고리명 조회

        //카테고리명이 비어있다면..
        if (categoryName == null || categoryName.isEmpty()) {
            return null;
        }

        //searchPlaces() 메소드를 이용해서 상위 5개 음식점 정보를 돌려준다
        return searchPlaces(categoryName);
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
    public void confirmVisitAndUpdateCategory(String userId, HistoryDTO history) {
        //1. 현재 회원 정보를 가져오기
        UserDTO user = userDAO.getUserById(userId);
        if(user == null) {
            throw new RuntimeException("유저를 찾을 수 없어요: " + userId);
        }

        //2. 특정 카테고리 방문 횟수 증가 (DTO 내부에서)
        int categoryId = history.getCategory_id2();
        user.incrementCategoryVisitCount(categoryId);

        //3. 변경된 정보를 DB에 반영
        userDAO.incrementCategoryVisitCount(userId, categoryId);

        //4. 방문한 식당 정보를 "방문한_식당_히스토리" 테이블에 삽입
        history.setUser_id2(userId); //JSON의 "user_id" 값을 VisitHistoryDTO의 "user_id2"로 매핑
        historyDAO.insertVisitHistory(history);
    }
}
