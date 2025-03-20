package com.uplus.matdori.category.model.dto;

import lombok.*;
import lombok.experimental.Accessors;

//lombok을 사용해서 Getter, Setter 바로 그냥 생성함
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
//히스토리 관련 DTO
public class HistoryDTO {
	private int history_id; // 히스토리 ID (INT / AUTO_INCREMENT)
    private String user_id2; // 회원 ID (VARCHAR 10 / JSON의 "user_id" 값 매핑)
    private int rate = 0; // 평점 (INT / 기본값 0 설정)
    private String url; // 식당 URL (TEXT)
    private String title; // 식당명 (VARCHAR(255))
    private String roadAddress; // 주소 (VARCHAR(255))
    private int category_id2; // 카테고리 ID (INT / JSON의 "category_id" 값 매핑)
}
