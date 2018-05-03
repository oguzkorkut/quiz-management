package com.quiz.service;

import java.util.List;

import com.quiz.dto.AnswerDto;
import com.quiz.dto.CategoryDto;
import com.quiz.dto.QuestionDto;

public interface IQuizSerice {
	
	public boolean save(CategoryDto categoryDto) throws Exception;
	
	public boolean update(CategoryDto categoryDto) throws Exception;
	
	public List<CategoryDto> getCategories() throws Exception;
	
	public List<QuestionDto> getQuestionsByCategoryId(int id) throws Exception;
	
	public boolean saveQuestion(QuestionDto questionDto) throws Exception;
	
	public boolean saveAnswer(AnswerDto answerDto) throws Exception;
	
	public boolean saveAnswers(List<AnswerDto> answerDtos) throws Exception;

}
