package com.uplus.matdori.category.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uplus.matdori.category.model.dao.HistoryDAO;
import com.uplus.matdori.category.model.dao.UserDAO;
import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.HistoryDTO;
import com.uplus.matdori.category.model.dto.UserDTO;

//History 관련 정보들을 처리하는 기능을 가진 HistoryServiceImp
@Service
public class HistoryServiceImp implements HistoryService {
    private final HistoryDAO historyDAO;
    private final UserDAO userDAO;
    
    public HistoryServiceImp(HistoryDAO historyDAO, UserDAO userDAO) {
        this.historyDAO = historyDAO;
        this.userDAO = userDAO;
    }

    @Override
    public ResponseEntity<ApiResponse<List<HistoryDTO>>> getUserHistory(String user_id) {
    	try {
    		UserDTO user = userDAO.getUserById(user_id);
    		
    		// 존재하지 않는 회원인 경우
    		if(user == null) {
    			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("존재하지 않는 사용자입니다."));
    		}
    		
    		List<HistoryDTO> historyList = historyDAO.getUserHistory(user_id);
    		
    		// 존재하는 회원의 히스토리가 없는 경우
			if(historyList == null) {
				List<HistoryDTO> emptyList = new ArrayList<>();
				return ResponseEntity.ok(ApiResponse.success(emptyList));
			}
			
			// 존재하는 회원의 히스토리가 존재하는 경우
			return ResponseEntity.ok(ApiResponse.success(historyList));
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
    }

    @Override
    public void rateHistory(HistoryDTO historyDTO) {

    }

    @Override
    public void deleteHistory(int historyId) {

    }
}
