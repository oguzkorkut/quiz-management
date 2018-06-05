package com.quiz.dao;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.model.Answer;
import com.quiz.repository.AnswerRepository;

@Service("answerDao")
@Transactional
public class AnswerDaoImpl implements IAnswerDao {

	private static final Logger logger = LogManager.getLogger(AnswerDaoImpl.class);

	@Autowired
	private AnswerRepository answerRepository;

	@Override
	public boolean save(Answer answer) throws Exception {
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("saveAnswer begins. ID: ");
			sb.append(answer.getId());
			logger.trace(sb.toString());
		}
		
		Answer answerResult = answerRepository.save(answer);
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("saveAnswer ends. ID: ");
			sb.append(answerResult.getId());
			logger.trace(sb.toString());
		}

		return true;
	}

	@Override
	public boolean update(Answer answer) throws Exception {
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("updateAnswer begins. ID: ");
			sb.append(answer.getId());
			logger.trace(sb.toString());
		}
		
		Answer answerResult = answerRepository.save(answer);
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("updateAnswer ends. ID: ");
			sb.append(answerResult.getId());
			logger.trace(sb.toString());
		}

		return true;
	}

	@Override
	public boolean delete(Integer id) throws Exception {

		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("deleteAnswer begins. ID: ");
			sb.append(id);
			logger.trace(sb.toString());
		}
		
		answerRepository.delete(id);
		
		if (logger.isTraceEnabled()) {
			StringBuilder sb = new StringBuilder();
			sb.append("deleteAnswer ends. ID: ");
			sb.append(id);
			logger.trace(sb.toString());
		}

		return true;
	}

}
