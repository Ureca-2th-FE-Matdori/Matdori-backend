package com.uplus.matdori.category.model.dto;

import java.io.Serializable;

public class Category implements Serializable {
	private static final long serialVersionUID=1L;
	private int category_id;
	private String category_name;

	public Category() {}
	public Category(int category_id, String category_name) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory(Category category) {
		this.category_id = category.getCategory_id();
		this.category_name = category.getCategory_name();
	}
//	public String getIsbn() {
//		return isbn;
//	}
//	public void setIsbn(String isbn) {
//		this.isbn = isbn;
//	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getAuthor() {
//		return author;
//	}
//	public void setAuthor(String author) {
//		this.author = author;
//	}
//	public int getPrice() {
//		return price;
//	}
//	public void setPrice(int price) {
//		this.price = price;
//	}
//	public String getDescrib() {
//		return describ;
//	}
//	public void setDescrib(String describ) {
//		this.describ = describ;
//	}
//	public String getImg() {
//		return img;
//	}
//	public void setImg(String img) {
//		this.img = img;
//	}
	@Override
	public String toString() {
		return "category_name은 " + category_name;
	}
	
	
}
