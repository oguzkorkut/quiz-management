package com.quiz.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.quiz.dao.IAnswerDao;
import com.quiz.dao.ICategoryDao;
import com.quiz.dao.IQuestionDao;
import com.quiz.dto.AnswerDto;
import com.quiz.dto.CategoryDto;
import com.quiz.dto.QuestionDto;
import com.quiz.factory.Factory;
import com.quiz.model.Answer;
import com.quiz.model.Category;
import com.quiz.model.Question;

@Service("quizSerice")
@Transactional
public class QuizSericeImpl implements IQuizSerice {

	private static final Logger logger = LogManager.getLogger(QuizSericeImpl.class);

	@Autowired
	private ICategoryDao categoryDao;

	@Autowired
	private IQuestionDao questionDao;
	
	@Autowired
	private IAnswerDao answerDao;
	
	@Autowired
	private Factory quizFactory;

	@Override
	public boolean save(CategoryDto categoryDto) throws Exception {
		return saveCategory(categoryDto);
	}

	@Override
	public boolean update(CategoryDto categoryDto) throws Exception {
		return saveCategory(categoryDto);
	}

	@Override
	public List<CategoryDto> getCategories() throws Exception {

		List<CategoryDto> categoryDtos = new ArrayList<CategoryDto>();
		
		try {
			List<Category> categories = categoryDao.getCategories();

			if (!CollectionUtils.isEmpty(categories)) {

				CategoryDto categoryDto = null;

				for (Category category : categories) {

					categoryDto = quizFactory.convertCategoryToCategoryDto(category);

					categoryDtos.add(categoryDto);
				}
			}
		} catch (Exception e) {
			logger.error(e, e);
		}
		return categoryDtos;
	}
	
	private boolean saveCategory(CategoryDto categoryDto) {
		try {
			Category category = quizFactory.convertCategoryDtoToCategory(categoryDto);

			categoryDao.save(category);
		} catch (Exception e) {
			logger.error(e, e);

			return false;
		}

		return true;
	}

	@Override
	public List<QuestionDto> getQuestionsByCategoryId(int id) throws Exception {
		List<QuestionDto> questionDtos = new ArrayList<QuestionDto>();
		try {
			List<Question> questions = questionDao.getQuestionsByCategoryId(id);
			
			if (!CollectionUtils.isEmpty(questions)) {
				QuestionDto questionDto = null;
				
				for (Question question : questions) {
					questionDto = quizFactory.convertQuestionToQuestionDto(question);
					
					questionDtos.add(questionDto);
				}
			}

		} catch (Exception e) {
			logger.error(e, e);
		}
		return questionDtos;
	}

	@Override
	public boolean saveQuestion(QuestionDto questionDto) throws Exception {
		try {
			Question question = quizFactory.convertQuestionDtoToQuestion(questionDto);

			Category category = categoryDao.getCategoryById(questionDto.getCategoryId());
			
			question.setCategory(category);
			
			questionDao.save(question);
		} catch (Exception e) {
			logger.error(e, e);

			return false;
		}

		return true;
	}

	@Override
	public boolean saveAnswer(AnswerDto answerDto) throws Exception {
		try {
			Answer answer = quizFactory.convertAnswerDtoToAnswer(answerDto);

			Question question = questionDao.getQuestionById(answerDto.getQuestionId());
			
			answer.setQuestion(question);
			
			answerDao.save(answer);
		} catch (Exception e) {
			logger.error(e, e);

			return false;
		}

		return true;
	}

	@Override
	public boolean saveAnswers(List<AnswerDto> answerDtos) throws Exception {
		try {
			
			Question question = questionDao.getQuestionById(answerDtos.get(0).getQuestionId());
			
			Answer answer = null;
			
			for (AnswerDto dto : answerDtos) {
				if (dto.isDeleted()) {
					answerDao.delete(dto.getId());
				} else {
					answer = quizFactory.convertAnswerDtoToAnswer(dto);
					answer.setQuestion(question);
					if (dto.getId() != null) {
						answerDao.update(answer);
					}  else {
						answerDao.save(answer);
					}
				}
			} 
			
		} catch (Exception e) {
			logger.error(e, e);

			return false;
		}

		return true;
	}

}
