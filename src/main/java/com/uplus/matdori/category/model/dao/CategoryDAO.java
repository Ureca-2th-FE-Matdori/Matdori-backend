package com.uplus.matdori.category.model.dao;

import com.uplus.matdori.category.model.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CategoryDAO {

	// 특정 카테고리 조회 (category_id로 category_name 찾기)
	String search(@Param("category_id") int categoryId);

	// 새로운 카테고리 추가
	void insert(CategoryDTO category);

	// 카테고리 삭제
	void delete(@Param("category_id") int categoryId);
	
	// 카테고리 존재 여부 확인
	Integer checkCategoryName(String categoryName);
}


