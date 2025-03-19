package com.uplus.matdori.category.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true) //불필요한 필드는 자동 무시
//응답의 items 리스트에 들어갈 개별 장소 정보를 담을 DTO
public class NaverPlaceDTO {
    private String title; //업체, 기관의 이름
    private String link; //업체, 기관의 상세 정보 URL
    private String category; //업체, 기관의 분류 정보
    private String description; //업체, 기관에 대한 설명
    private String roadAddress; //업체, 기관명의 도로명 주소
    private String mapx; //업체, 기관이 위차한 장소의 x좌표(KATECH 좌표계 기준), 네이버 지도 API에서 사용 가능
    private String mapy; //업체, 기관이 위차한 장소의 y좌표(KATECH 좌표계 기준), 네이버 지도 API에서 사용 가능
}
