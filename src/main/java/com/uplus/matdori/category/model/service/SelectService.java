package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dto.CategoryDTO;
import com.uplus.matdori.category.model.dto.HistoryDTO;
import com.uplus.matdori.category.model.dto.NaverLocalResponseDTO;

public interface SelectService {
    NaverLocalResponseDTO getRandomCategory();
    void confirmVisitAndUpdateCategory(String userId, HistoryDTO history);
    String getPreferredCategory(String userId);
    NaverLocalResponseDTO testSearchWithLocation(String categoryName);
}
