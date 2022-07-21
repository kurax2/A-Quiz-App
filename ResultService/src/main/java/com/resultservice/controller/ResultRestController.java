package com.resultservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.resultservice.result.Result;
import com.resultservice.result.ResultService;

@RestController
@RequestMapping("/abc-university/result")
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
	public List<Result> getAllResultsByUserID(@PathVariable String id)
	{
		return resultService.getAllResultsByUserID(id);
	}
	
	//USER u
	@PutMapping("/update")
	public ResponseEntity<Result> updateResult(@RequestBody Result u)
	{
		Result updateResult = resultService.updateResult(u);
		return new ResponseEntity<>(updateResult,HttpStatus.OK);
	}

	//RESULT c
	@PostMapping("/add")
	public Result addResult(@RequestBody @Valid Result r) {
		return resultService.addResult(r);
	}
	
	
}
