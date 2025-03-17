package com.uplus.matdori.category.model.dao;

import com.uplus.matdori.category.model.dto.CategoryDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryDAO {

	// 특정 카테고리 조회
	@Select("SELECT * FROM category WHERE category_id = #{category_id}")
	CategoryDTO getCategoryById(@Param("category_id") int category_id);

	// 전체 카테고리 조회
	@Select("SELECT * FROM category")
	List<CategoryDTO> getAllCategories();

	// 새 카테고리 추가
	@Insert("INSERT INTO category (category_id, category_name) VALUES (#{category_id}, #{category_name})")
	void insertCategory(CategoryDTO categoryDTO);

	// 카테고리 수정
	@Update("UPDATE category SET category_name = #{category_name} WHERE category_id = #{category_id}")
	void updateCategory(CategoryDTO categoryDTO);

	// 카테고리 삭제
	@Delete("DELETE FROM category WHERE category_id = #{category_id}")
	void deleteCategory(@Param("category_id") int category_id);
}

