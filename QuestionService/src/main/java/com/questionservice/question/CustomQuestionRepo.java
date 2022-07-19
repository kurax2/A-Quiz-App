package com.questionservice.question;

import java.util.List;

public interface CustomQuestionRepo {
	public List<Question> getQuestionByCat(String category);
	

}
