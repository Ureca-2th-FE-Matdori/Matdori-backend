package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.HistoryDTO;
import com.uplus.matdori.category.model.dto.NaverLocalResponseDTO;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface HistoryService {
    List<HistoryDTO> getUserHistory(String userId);
    void rateHistory(HistoryDTO historyDTO);
    ResponseEntity<ApiResponse<Object>> deleteHistory(int histor_id, String user_id);
}
