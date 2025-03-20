package com.uplus.matdori.category.model.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.HistoryDTO;

public interface HistoryService {
	ResponseEntity<ApiResponse<List<HistoryDTO>>> getUserHistory(String user_id);
    void rateHistory(HistoryDTO historyDTO);
    void deleteHistory(int historyId);
}
