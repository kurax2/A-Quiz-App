package com.resultservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping("/result/all")
	public List<Result> getAllResult(){
			
		return resultService.getAllResult();
	}

}
