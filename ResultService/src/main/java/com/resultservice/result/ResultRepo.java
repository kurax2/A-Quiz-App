package com.resultservice.result;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepo extends JpaRepository<Result,Integer>{

	List<Result> getResultByCat(String category);

	List<Result> getAllQuestionsByStudentID(String id);

}
