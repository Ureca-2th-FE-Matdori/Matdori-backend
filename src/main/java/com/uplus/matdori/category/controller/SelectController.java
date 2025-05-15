package com.uplus.matdori.category.controller;

import com.uplus.matdori.category.model.dao.CategoryDAO;
import com.uplus.matdori.category.model.dao.HistoryDAO;
import com.uplus.matdori.category.model.dao.UserDAO;
import com.uplus.matdori.category.model.dto.*;
import com.uplus.matdori.category.model.service.HistoryService;
import com.uplus.matdori.category.model.service.SelectService;
import com.uplus.matdori.category.model.service.SelectServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/*
 * @RestController
 *   RestFul Service를 위한 Conrtoller
 *   모든 메서드의 응답을  @ResponseBody를 붙여주는 효과
 */
@RestController

//RestFul에서 서비스할 자원(Domain)명을 붙인다
@RequestMapping("/select")

/*
 * @CrossOrigin
 *  - CORS 요청에 대한 승인
 *  origins = {"*"}  : 요청하는 모든 URL, 메서드를 승인
 *     ==> 보안에 취약하므로 상용에서는 사용 안함
 *     ==> 이후에 Configuration을 통해 설정할 예정
 * */
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

//뽑기 관련 컨트롤러
@Slf4j
public class SelectController {
    private final SelectService selectService;
    private final HistoryService historyService;
    private final HistoryDAO historyDAO;

    public SelectController(SelectService selectService, HistoryService historyService, HistoryDAO historyDAO) {
        this.selectService = selectService;
        this.historyService = historyService;
        this.historyDAO = historyDAO;
    }
  
    @PostMapping("/finalize")
    public ResponseEntity<ApiResponse<Object>> confirmVisitAndUpdateCategory(@RequestBody VisitRequestDTO request) {
        log.info("요청이 SelectController에 도착했는지 확인: {}", request.getUser_id());

        //VisitRequestDTO를 HistoryDTO로 변환
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setUser_id2(request.getUser_id()); // 기존 HistoryDTO의 user_id2 필드
        historyDTO.setTitle(request.getRestaurant_info().getTitle());
        historyDTO.setRoadAddress(request.getRestaurant_info().getRoadAddress());
        historyDTO.setUrl(request.getRestaurant_info().getUrl());
        historyDTO.setRate(0); // 기본적으로 0 설정
        historyDTO.setCategory_id2(request.getRestaurant_info().getCategory_id()); //카테고리 id 레츠고

        //DAO에 있는 저장 함수 넣고 DB에 저장
        return selectService.confirmVisitAndUpdateCategory(historyDTO.getUser_id2(), historyDTO);
    }
  
    @GetMapping({"/random", "/{selectCategoryName}"})
    public ResponseEntity<ApiResponse<NaverCategoryResponseDTO>> getRandomCategory(@RequestParam double latitude, @RequestParam double longitude, @PathVariable Optional<String> selectCategoryName) {
        return selectService.getRandomCategory(latitude, longitude, selectCategoryName.orElse(null));
    }
    
    @GetMapping("/prefer/{userId}")
    public ResponseEntity<ApiResponse<NaverCategoryResponseDTO>> getPreferCategory(@RequestParam double latitude, @RequestParam double longitude, @PathVariable String userId) {
        String categoryName = selectService.getPreferredCategory(userId);
    	return selectService.getRandomCategory(latitude, longitude, categoryName);
    }
}
