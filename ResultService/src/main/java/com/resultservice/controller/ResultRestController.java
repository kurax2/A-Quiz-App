package com.resultservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resultservice.result.Result;
import com.resultservice.result.ResultService;

@RestController
@RequestMapping("/api/result")
@Validated
public class ResultRestController {
	
	@Autowired
	ResultService resultService;
	
	//Result R
	@GetMapping("/all")
	public List<Result> getAllResult(){
			
		return resultService.getAllResult();
	}
	
	@GetMapping("/all/cat/{category}")
	public List<Result> getAllQuestionsByCat(@PathVariable String category)
	{
		return resultService.getResultByCat(category);
	}
	
	@GetMapping("/all/student/{id}")
	public List<Result> getAllResultsByStudentID(@PathVariable String id)
	{
		return resultService.getAllQuestionsByStudentID(id);
	}

	//RESULT c
	@PostMapping("/add")
	public Result addResult(@RequestBody @Valid Result r) {
		return resultService.addResult(r);
	}
}
