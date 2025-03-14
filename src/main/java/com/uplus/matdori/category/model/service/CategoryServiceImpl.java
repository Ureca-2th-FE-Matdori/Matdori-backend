package com.uplus.matdori.category.model.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.uplus.matdori.category.model.dao.CategoryDao;
import com.uplus.matdori.category.model.dto.Category;
import com.uplus.matdori.category.model.dto.CategoryException;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private CategoryDao dao;
	public CategoryServiceImpl(CategoryDao dao) {
		this.dao = dao;
	}
//	@Override
//	public void insert(Book book) {
//		try {
//			Book find = dao.search(book.getIsbn());
//			if(find!=null)
//				throw new BookException("이미 등록된 isbn입니다.");
//			
//			dao.insert(book);
//		} catch (SQLException e) {
//			throw new BookException("책 정보 등록 중 오류 발생");
//		}
//	}	
	@Override
	public void insert(Category category) {
		try {
			dao.insert(category);
		} catch (SQLException e) {
			throw new CategoryException("새로운 카테고리 등록 중 오류 발생");
		}
		
	}
//
//	@Override
//	public void update(Book book) {
//		try {
//			Book find = dao.search(book.getIsbn());
//			if(find == null) throw new BookException("등록되지 않은 책 정보를 수정할 수 없습니다.");
//			dao.update(book);
//		} catch (SQLException e) {
//			throw new BookException("책 정보 수정 중 오류 발생");
//		}
//	}

	@Override
	public String search(int category_id) {
		try {
			String category = dao.search(category_id);
			if(category == null) {
				throw new CategoryException("요청한 책은 등록되지 않은 책 정보입니다.");
			}
			return category;
		} catch (SQLException e) {
			throw new CategoryException("책 정보 조회 중 오류 발생");
		}
	}
//	@Override
//	public List<Book> searchAll(PageBean bean) {
//		System.out.println("BookService.searchAll 수행 중...............");
//		try {
//			int total = dao.totalCount(bean);
//			PageUtility page = new PageUtility(bean.getInterval(), total, bean.getPageNo(), null);
//			bean.setPageLink(page.getPageBar());
//			return dao.searchAll(bean);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new BookException("책 목록 정보 조회 중 오류 발생");
//		}
//	}
	@Override
	public void delete(int category_id) {
		try {
			dao.delete(category_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CategoryException("카테고리 정보 삭제 중 오류 발생");
		}
	}
}
