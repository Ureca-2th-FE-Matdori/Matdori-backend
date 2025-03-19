package com.uplus.matdori.category.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRankingDTO {
	private String user_id;
    private int point;
}
