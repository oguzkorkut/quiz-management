package com.quiz.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.model.Question;
import com.quiz.repository.QuestionRepository;

@Service("questionDao")
@Transactional
public class QuestionDaoImpl implements IQuestionDao {

	private static final Logger logger = LogManager.getLogger(QuestionDaoImpl.class);
	
	@Autowired
	private QuestionRepository questionRepository; 

	@Override
	public List<Question> getQuestionsByCategoryId(int id) throws Exception {
		
		List<Question> questions = questionRepository.findByCategoryId(id);
		
		return questions;
	}

	@Override
	public boolean save(Question question) throws Exception {
		questionRepository.save(question);
		return true;
	}

	@Override
	public Question getQuestionById(Integer id) throws Exception {
		Question question = questionRepository.findOne(id);
		return question;
	}
	
}
