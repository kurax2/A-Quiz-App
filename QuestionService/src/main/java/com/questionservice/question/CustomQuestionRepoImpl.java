package com.questionservice.question;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;


public class CustomQuestionRepoImpl implements CustomQuestionRepo {
	
	@Autowired
	EntityManager springDataJPA;
	
	
	@Override
	public List<Question> getQuestionByCat(String category) {
		String query = "from Question q where q.category=:category";
		Query q = springDataJPA.createQuery(query,Question.class);
		q.setParameter("category",category);
		List<Question> questionList=q.getResultList();
		return questionList;
		
	}



	

}
