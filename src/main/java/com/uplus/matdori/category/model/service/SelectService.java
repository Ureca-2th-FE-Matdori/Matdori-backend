package com.uplus.matdori.category.model.service;

import org.springframework.http.ResponseEntity;

import com.uplus.matdori.category.model.dto.ApiResponse;
import com.uplus.matdori.category.model.dto.CategoryDTO;
import com.uplus.matdori.category.model.dto.NaverLocalResponseDTO;

public interface SelectService {
    ResponseEntity<ApiResponse<NaverLocalResponseDTO>> getRandomCategory(String selectCategoryName);
  
    String getPreferredCategory(String userId);
}
