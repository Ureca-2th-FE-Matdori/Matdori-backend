package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dto.CategoryDTO;
import com.uplus.matdori.category.model.dto.NaverLocalResponseDTO;

public interface SelectService {
    NaverLocalResponseDTO getRandomCategory();
    String getPreferredCategory(String userId);
}
