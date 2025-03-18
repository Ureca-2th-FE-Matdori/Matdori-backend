package com.uplus.matdori.category.model.dto;

import lombok.*;

//lombok을 사용해서 Getter, Setter 바로 그냥 생성함
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//히스토리 관련 DTO
public class HistoryDTO {
    private String userId; // 회원 ID (VARCHAR 10)
    private String url; // 식당 URL (TEXT)
    private int rate; // 평점 (INT)
    private int historyId; // 히스토리 ID (INT)
    private int categoryId; // 카테고리 ID (INT)
}
