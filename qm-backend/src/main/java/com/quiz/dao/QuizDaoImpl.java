package com.quiz.dao;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.repository.AnswerRepository;
import com.quiz.repository.CategoryRepository;
import com.quiz.repository.QuestionRepository;

@Service("quizDao")
@Transactional
public class QuizDaoImpl implements IQuizDao {
	
	private static final Logger logger = LogManager.getLogger(QuizDaoImpl.class);
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
}
