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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


import static java.rmi.server.LogStream.log;


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

    //특정 방문 내역의 평점(rate)을 업데이트하는 메소드
    @Override
    @Transactional // 데이터 일관성을 위해 트랜잭션 적용
    public ResponseEntity<ApiResponse<Object>> rateHistory(int history_id, int rate) {
        // history_id가 유효한지 조회
        HistoryDTO history = historyDAO.findById(history_id);

        if (history == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("유효하지 않은 history_id 값이에요!"));
        }

        log.info(history.toString());

        // rate 값이 0~5 범위를 벗어나면 예외 발생
        if (rate < 0 || rate > 5) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("평점은 0~5사이어야 하잖아 이자쉭아"));
        }

        // 기존 데이터의 rate를 업데이트
        history.setRate(rate);
        historyDAO.updateRating(history); // DB에 반영

        //success일 경우에는 Map.of() 넘겨서.. content는 빈 객체를 넘김
        return ResponseEntity.ok(ApiResponse.success(Map.of()));
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
