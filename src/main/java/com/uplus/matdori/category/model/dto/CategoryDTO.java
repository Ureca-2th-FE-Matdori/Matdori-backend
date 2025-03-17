package com.uplus.matdori.category.model.dto;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
	private int category_id;
	private String category_name;

	// 기본 생성자
	public CategoryDTO() {}

	// 모든 필드를 포함한 생성자
	public CategoryDTO(int category_id, String category_name) {
		this.category_id = category_id;
		this.category_name = category_name;
	}

	// Getter & Setter
	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	// toString() 메서드
	@Override
	public String toString() {
		return "CategoryDTO { category_id = " + category_id + ", category_name = '" + category_name + "' }";
	}
}

