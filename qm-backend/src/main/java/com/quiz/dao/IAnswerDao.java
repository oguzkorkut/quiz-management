package com.quiz.dao;

import com.quiz.model.Answer;

public interface IAnswerDao {

	public boolean save(Answer answer)  throws Exception;
	
	public boolean update(Answer answer)  throws Exception;
	
	public boolean delete(Integer id)  throws Exception;
	
}
