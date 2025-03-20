package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.HistoryDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HistoryService {
    List<HistoryDTO> getUserHistory(String userId);
    ResponseEntity<ApiResponse<Object>> rateHistory(int history_id, int rate);
    void deleteHistory(int historyId);
}
