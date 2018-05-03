package com.quiz.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryDto {

	private Integer id;

	private String category;

	private Date createdDate;

	private String createdBy;

	private Date updatedDate;

	private String updatedBy;

	private boolean active;

	private List<QuestionDto> questionDtos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<QuestionDto> getQuestionDtos() {
		if (questionDtos == null) {
			questionDtos = new ArrayList<QuestionDto>();
		}
		return questionDtos;
	}

	public void setQuestionDtos(List<QuestionDto> questions) {
		this.questionDtos = questions;
	}

}
