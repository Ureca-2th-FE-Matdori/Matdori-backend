package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dto.*;
import org.springframework.http.ResponseEntity;

public interface SelectService {
	ResponseEntity<ApiResponse<NaverCategoryResponseDTO>> getRandomCategory(double latitude, double longitude, String selectCategoryName);
    ResponseEntity<ApiResponse<Object>> confirmVisitAndUpdateCategory(String userId, HistoryDTO history);

    String getPreferredCategory(String userId);
    NaverLocalResponseDTO testSearchWithLocation(String categoryName, double latitude, double longitude);
}
