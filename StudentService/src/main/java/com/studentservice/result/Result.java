package com.studentservice.result;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Result {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int testId;
	private int userId;
	private String date;
	private String Category;
	private String Level;
	private String totalScore;
	private int marks;
	
	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Result(int userId, String date, String category, String level, String totalScore, int marks) {
		super();
		this.userId = userId;
		this.date = date;
		Category = category;
		Level = level;
		this.totalScore = totalScore;
		this.marks = marks;
	}



	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Category, Level, date, marks, testId, totalScore, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		return Objects.equals(Category, other.Category) && Objects.equals(Level, other.Level)
				&& Objects.equals(date, other.date) && marks == other.marks && testId == other.testId
				&& Objects.equals(totalScore, other.totalScore) && userId == other.userId;
	}

	@Override
	public String toString() {
		return "Result [testId=" + testId + ", userId=" + userId + ", date=" + date + ", Category=" + Category
				+ ", Level=" + Level + ", totalScore=" + totalScore + ", marks=" + marks + "]";
	}
	
	
	
	

}
