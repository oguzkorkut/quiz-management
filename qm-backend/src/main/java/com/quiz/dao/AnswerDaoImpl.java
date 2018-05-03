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
		answerRepository.save(answer);
		return true;
	}

	@Override
	public boolean update(Answer answer) throws Exception {
		answerRepository.save(answer);
		return true;
	}

	@Override
	public boolean delete(Integer id) throws Exception {
		answerRepository.delete(id);
		return true;
	}
	
}
