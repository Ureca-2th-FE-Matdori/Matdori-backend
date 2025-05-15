package com.uplus.matdori.category.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 뽑기 응답과 관련해서 "선택된 카테고리명"도 포함하기 위한 NaverCategoryResponseDTO 정의
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NaverCategoryResponseDTO {
    private String categoryName;
    private NaverLocalResponseDTO response;
}