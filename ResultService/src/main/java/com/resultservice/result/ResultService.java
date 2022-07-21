package com.resultservice.result;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {
	
	@Autowired
	ResultRepo resultRepo;
	
	public Result addResult(Result r) {
		
		Result savedEntity= resultRepo.save(r);
		return savedEntity;
	}
	
	public List<Result> getAllResult(){
		return resultRepo.findAll();
	}
	
	public Result updateResult(Result r) {
		return resultRepo.save(r);
	}
	
	@Transactional
	public void deleteResultById(int resultId) {
		resultRepo.deleteById(resultId);
		
	}

	public Result getResultById(int resultId) {
		return resultRepo.getById(resultId);
	}

}
