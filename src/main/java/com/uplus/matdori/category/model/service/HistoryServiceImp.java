package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dao.HistoryDAO;
import com.uplus.matdori.category.model.dao.UserDAO;
import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.HistoryDTO;
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

        // user_id가 존재하는지도 확인
        UserDTO user = userDAO.getUserById(history.getUser_id2());

        // history에 있는 user_id2 값이 유효하지 않아서, user를 제대로 못 불러온 경우
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("유효하지 않은 history_id 값이에요!"));
        }

        // 기존 user에서 point를 올려주고, 이를 DAO로 적용시킨다
        userDAO.increaseUserPoint(history.getUser_id2());

        //success일 경우에는 Map.of() 넘겨서.. content는 빈 객체를 넘김
        return ResponseEntity.ok(ApiResponse.success(Map.of()));
    }

    @Override
    public void deleteHistory(int historyId) {
        //아직 구현 안해놓음
    }
}
