package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.CategoryDTO;
import com.uplus.matdori.category.model.dto.HistoryDTO;
import com.uplus.matdori.category.model.dto.NaverLocalResponseDTO;
import org.springframework.http.ResponseEntity;

public interface SelectService {
	ResponseEntity<ApiResponse<NaverLocalResponseDTO>> getRandomCategory(double latitude, double longitude, String selectCategoryName);
    ResponseEntity<ApiResponse<Object>> confirmVisitAndUpdateCategory(String userId, HistoryDTO history);

    String getPreferredCategory(String userId);
    NaverLocalResponseDTO testSearchWithLocation(String categoryName, double latitude, double longitude);
}
