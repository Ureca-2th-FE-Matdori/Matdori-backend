package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dto.CategoryDTO;

public interface SelectService {
    String getRandomCategory();
    String getPreferredCategory(String userId);
}
