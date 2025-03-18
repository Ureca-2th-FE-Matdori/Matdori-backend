package com.uplus.matdori.category.model.dto;

import lombok.*;

import java.io.Serializable;

//lombok을 사용해서 Getter, Setter 바로 그냥 생성함
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDTO implements Serializable {
	private int category_id;
	private String category_name;
}

