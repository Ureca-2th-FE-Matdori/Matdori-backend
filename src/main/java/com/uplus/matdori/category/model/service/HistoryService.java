package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.HistoryDTO;

import com.uplus.matdori.category.model.dto.NaverLocalResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface HistoryService {
    List<HistoryDTO> getUserHistory(String userId);
    ResponseEntity<ApiResponse<Object>> deleteHistory(int histor_id, String user_id);

    ResponseEntity<ApiResponse<Object>> rateHistory(int history_id, int rate);
}
