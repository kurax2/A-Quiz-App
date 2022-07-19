package com.studentservice.question;

import java.util.List;

public interface CustomQuestionRepo {
	public List<Question> getQuestionByCat(String category);
	

}
