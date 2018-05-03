package com.quiz.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.quiz.dto.AnswerDto;
import com.quiz.dto.CategoryDto;
import com.quiz.dto.QuestionDto;
import com.quiz.model.Answer;
import com.quiz.model.Category;
import com.quiz.model.Question;

@Service("quizFactory")
public class Factory {

	public CategoryDto convertCategoryToCategoryDto(Category category) {
		CategoryDto categoryDto = new CategoryDto();

		BeanUtils.copyProperties(category, categoryDto);

		if (!CollectionUtils.isEmpty(category.getQuestions())) {

			QuestionDto questionDto = null;
			
			List<Question> questions = new ArrayList<Question>(category.getQuestions());

			for (Question question : questions) {

				questionDto = new QuestionDto();

				BeanUtils.copyProperties(question, questionDto);
				
				questionDto.setCategoryId(category.getId());

				if (!CollectionUtils.isEmpty(question.getAnswers())) {
					AnswerDto answerDto = null;
					
					List<Answer> answers = new ArrayList<Answer>(question.getAnswers());

					for (Answer answer : answers) {
						answerDto = new AnswerDto();

						BeanUtils.copyProperties(answer, answerDto);
						
						answerDto.setQuestionId(question.getId());

						questionDto.getAnswerDtos().add(answerDto);
					}
				}
				categoryDto.getQuestionDtos().add(questionDto);
			}
		}

		return categoryDto;
	}

	public Category convertCategoryDtoToCategory(CategoryDto categoryDto) {
		Category category = new Category();

		BeanUtils.copyProperties(categoryDto, category);

		if (!CollectionUtils.isEmpty(categoryDto.getQuestionDtos())) {
			Question question = null;

			for (QuestionDto qDto : categoryDto.getQuestionDtos()) {
				question = new Question();

				BeanUtils.copyProperties(qDto, question);

				if (!CollectionUtils.isEmpty(qDto.getAnswerDtos())) {
					Answer answer = null;

					for (AnswerDto aDto : qDto.getAnswerDtos()) {
						answer = new Answer();

						BeanUtils.copyProperties(aDto, answer);

						question.getAnswers().add(answer);
					}
				}
				category.getQuestions().add(question);
			}
		}
		return category;
	}

	public QuestionDto convertQuestionToQuestionDto(Question question) {
		QuestionDto questionDto = new QuestionDto();

		BeanUtils.copyProperties(question, questionDto);
		
		questionDto.setCategoryId(question.getCategory().getId());

		if (!CollectionUtils.isEmpty(question.getAnswers())) {

			AnswerDto answerDto = null;
			
			List<Answer> answers = new ArrayList<Answer>(question.getAnswers());

			for (Answer answer : answers) {
				answerDto = new AnswerDto();

				BeanUtils.copyProperties(answer, answerDto);
				
				answerDto.setQuestionId(question.getId());

				questionDto.getAnswerDtos().add(answerDto);
			}
		}

		return questionDto;
	}
	
	
	public Question convertQuestionDtoToQuestion(QuestionDto questionDto) {
		Question question = new Question();

		BeanUtils.copyProperties(questionDto, question);
		
		if (!CollectionUtils.isEmpty(questionDto.getAnswerDtos())) {

			Answer answer = null;

			for (AnswerDto answerDto : questionDto.getAnswerDtos()) {
				answer = new Answer();

				BeanUtils.copyProperties(answerDto, answer);
				
				answer.setQuestion(question);

				question.getAnswers().add(answer);
			}
		}

		return question;
	}
	
	public Answer convertAnswerDtoToAnswer(AnswerDto answerDto) {
		Answer answer = new Answer();

		BeanUtils.copyProperties(answerDto, answer);

		return answer;
	}
	

}
