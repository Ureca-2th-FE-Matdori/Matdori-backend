package com.uplus.matdori.category.model.dto;

public class CategoryResponseDto {
    private String categoryName;

    public CategoryResponseDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
