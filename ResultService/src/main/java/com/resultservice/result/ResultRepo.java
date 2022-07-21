package com.resultservice.result;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepo extends JpaRepository<Result,Integer>{

	List<Result> getResultByCategory(String category);

	List<Result> getAllResultsByUserID(String id);

	void deleteResultByUserID(int userId);

}
