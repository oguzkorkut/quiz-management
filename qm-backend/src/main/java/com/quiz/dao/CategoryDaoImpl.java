package com.quiz.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.model.Category;
import com.quiz.repository.CategoryRepository;

@Service("categoryDao")
@Transactional
public class CategoryDaoImpl implements ICategoryDao{

	private static final Logger logger = LogManager.getLogger(CategoryDaoImpl.class);

	@Autowired
	private CategoryRepository  categoryRepository; 
	
	@Override
	public boolean save(Category category) throws Exception {
		
		categoryRepository.save(category);
		return true;
	}

	@Override
	public boolean update(Category category) throws Exception {
		categoryRepository.save(category);
		return true;
	}

	@Override
	public List<Category> getCategories() throws Exception {
		List<Category> categories =  categoryRepository.findAll();
		return categories;
	}

	@Override
	public Category getCategoryById(Integer id) throws Exception {
		Category category = categoryRepository.findOne(id);
		return category;
	}
	
}
