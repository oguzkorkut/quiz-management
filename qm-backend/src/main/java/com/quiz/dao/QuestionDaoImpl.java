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

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getQuestionsByCategoryId begins. ID: ");
			sb.append(id);
			logger.trace(sb.toString());
		}
		List<Question> questions = questionRepository.findByCategoryId(id);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getQuestionsByCategoryId ends. ID: ");
			sb.append(id);
			logger.trace(sb.toString());
		}

		return questions;
	}

	@Override
	public boolean save(Question question) throws Exception {
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("saveCategory begins. ID: ");
			sb.append(question.getId());
			logger.trace(sb.toString());
		}
		Question questionResult = questionRepository.save(question);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("saveCategory ends. ID: ");
			sb.append(questionResult.getId());
			logger.trace(sb.toString());
		}

		return true;
	}

	@Override
	public Question getQuestionById(Integer id) throws Exception {
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getQuestionById begins. ID: ");
			sb.append(id);
			logger.trace(sb.toString());
		}
		Question question = questionRepository.findOne(id);

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("getQuestionById ends. ID: ");
			sb.append(id);
			logger.trace(sb.toString());
		}
		
		return question;
	}

}
