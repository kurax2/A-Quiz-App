package com.studentservice.question;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class QuestionService {
	
	@Autowired
	QuestionRepo questionRepo;
	
	public Question addQuestion(Question u) {
		Question savedEntity =  questionRepo.save(u);
		return savedEntity;
	}

	public List<Question> getAllQuestions() {
		
		return questionRepo.findAll();
	}

	public Question updateQuestions(Question u) {
		
		return questionRepo.save(u);
	}
	
	@Transactional
	public void deleteQuestionByID(int QuestionId) {
		questionRepo.deleteById(QuestionId);
		
		
	}

}
