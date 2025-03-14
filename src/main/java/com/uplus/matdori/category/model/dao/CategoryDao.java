package com.uplus.matdori.category.model.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.uplus.matdori.category.model.dto.Category;



@Mapper // Spring Container를 통해 mybatis의 연동될 interface로 인식해서 구현체를 생성한다.
public interface CategoryDao {
//	public List<Book> searchAll(PageBean bean) throws SQLException;
//	public int totalCount(PageBean bean) throws SQLException;
	public String search(int category_id) throws SQLException;
//	public void remove(String isbn)	throws SQLException;
//	public void update(Book book)	throws SQLException;
//	public void insert(Book book)	throws SQLException;
	public void insert(Category category) throws SQLException;
	public void delete(int category_id) throws SQLException;
}
