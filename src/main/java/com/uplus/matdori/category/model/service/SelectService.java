package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dto.CategoryDTO;

public interface SelectService {
    CategoryDTO getRandomCategory();
    CategoryDTO getPreferredCategory(String userId);
    CategoryDTO getCompletelyRandomSelection();
}
