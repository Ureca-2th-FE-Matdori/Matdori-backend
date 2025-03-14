package com.uplus.matdori.category.model.service;

import com.uplus.matdori.category.model.dto.Category;

public interface CategoryService {
//	public void insert(Book book);
//	public void update(Book book);
	public String search(int category_id);
	public void insert(Category category);
	public void delete(int category_id);
//	public List<Book> searchAll(PageBean bean);
//	public void remove(String isbn);
}
