package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dao.HistoryDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.uplus.matdori.category.model.dao.UserDAO;
import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.HistoryDTO;
import com.uplus.matdori.category.model.dto.HistoryRequestDTO;
import com.uplus.matdori.category.model.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

//History 관련 정보들을 처리하는 기능을 가진 HistoryServiceImp
@Slf4j
@Service
public class HistoryServiceImp implements HistoryService {
    private final HistoryDAO historyDAO;
    private final UserDAO userDAO;
    private static final Logger logger = LoggerFactory.getLogger(SelectServiceImp.class);

    public HistoryServiceImp(HistoryDAO historyDAO, UserDAO userDAO) {
        this.historyDAO = historyDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<HistoryDTO> getUserHistory(String userId) {
        return List.of();
    }

    @Override
    public void rateHistory(HistoryDTO historyDTO) {

    }

    @Override
    public ResponseEntity<ApiResponse<Object>> deleteHistory(int history_id, String user_id) {
    	try {
    		UserDTO user = userDAO.getUserById(user_id);

        	// user 검사
        	if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("존재하지 않은 사용자입니다."));
            }
        	
        	// history 검사    	
        	HistoryRequestDTO requestDTO = new HistoryRequestDTO(history_id, user_id);
        	HistoryDTO history = historyDAO.getHistoryByUserIdAndHistoryId(requestDTO);

        	if (history == null) {
        	    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        	            .body(ApiResponse.error("존재하지 않는 히스토리입니다."));
        	}

        	// history_id, user_id로 삭제 수행
        	historyDAO.deleteHistory(history_id);
        	return ResponseEntity.ok(ApiResponse.success(Map.of()));
    	} catch (Exception e) {
	         throw new RuntimeException(e);
	    }
    }
}
