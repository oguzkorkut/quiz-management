package com.quiz.dao;

import java.util.List;

import com.quiz.model.Category;

public interface ICategoryDao {
	public boolean save(Category category)  throws Exception;
	
	public boolean update(Category category)  throws Exception;
	
	public List<Category> getCategories() throws Exception;
	
	public Category getCategoryById(Integer id) throws Exception;
}
