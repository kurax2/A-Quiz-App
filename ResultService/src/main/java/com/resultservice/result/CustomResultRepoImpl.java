package com.resultservice.result;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomResultRepoImpl implements CustomResultRepo {
	
	@Autowired
	EntityManager springDataJPA;
	
	ResultRepo resultRepo;
	
	@Override
	public List<Result> getResultByCategory(String category) {
		String query = "from Result q where q.category=:category";
		Query q = springDataJPA.createQuery(query,Result.class);
		q.setParameter("category",category);
		List<Result> resultList=q.getResultList();
		return resultList;
	}

	@Override
	public List<Result> getAllResultsByUserID(String id) {
		String query = "from Result q where q.userId=:id";
		Query q = springDataJPA.createQuery(query,Result.class);
		q.setParameter("id",id);
		List<Result> resultList=q.getResultList();
		return resultList;
	}

	@Override
	public void deleteResultByUserID(int userId) {
		String query = "Delete from Result q where q.userId=:id";
		Query q = springDataJPA.createQuery(query,Result.class);
		q.setParameter("id",userId);
		
		
	}

}
