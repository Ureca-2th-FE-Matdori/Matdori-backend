package com.uplus.matdori.category.model.dto;

import lombok.Getter;
import lombok.Setter;

//클라이언트에서 넘어오는 JSON 데이터와 관련한 VisitRequestDTO
@Getter
@Setter
public class VisitRequestDTO {
    private String user_id; // 클라이언트에서 "user_id" 값을 받음
    private RestaurantInfo restaurant_info; //내부 객체 (title, roadAddress, url)

    @Getter
    @Setter
    public static class RestaurantInfo {
        private int category_id;
        private String title;
        private String roadAddress;
        private String url;
    }
}
