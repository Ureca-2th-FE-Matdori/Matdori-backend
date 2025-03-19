package com.uplus.matdori.category.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//네이버 지역 검색 API 응답을 담을 DTO
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true) //응답 중 사용하지 않는 필드 무시
public class NaverLocalResponseDTO {
    private int total; //총 검색 결과 갯수
    private int display; //한 번에 표시할 검색 결과 갯수
    private List<NaverPlaceDTO> items; //검색된 장소 리스트
}
