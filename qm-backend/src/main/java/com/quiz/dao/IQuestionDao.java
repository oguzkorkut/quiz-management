package com.quiz.dao;

import java.util.List;

import com.quiz.model.Question;

public interface IQuestionDao {
	
	public boolean save(Question question)  throws Exception;
	
	public List<Question> getQuestionsByCategoryId(int id) throws Exception;
	
	public Question getQuestionById(Integer id) throws Exception;

}
