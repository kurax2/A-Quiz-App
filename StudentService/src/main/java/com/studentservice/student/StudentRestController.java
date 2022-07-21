package com.studentservice.student;

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

import com.studentservice.question.Question;
import com.studentservice.question.QuestionRepo;
import com.studentservice.question.QuestionService;
import com.studentservice.result.Result;
import com.studentservice.result.ResultService;

@RestController
@RequestMapping("/api/student")
@Validated
public class StudentRestController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuestionRepo questionRepo;
	
	@Autowired
	ResultService resultService;

	public StudentRestController() {
		
		System.out.println("Student Controller constructor called");
	}
	
	//Question R
	@GetMapping("/question/all")
	public List<Question> getAllQuestion(){
		return questionService.getAllQuestions();		
	}
	
	@GetMapping("/question/all/{category}")
	public List<Question> getAllQuestionByCat(@PathVariable String category){
		return questionRepo.getQuestionByCat(category);
	}
	
	//Result C
	@PostMapping("/result/add")
	public Result addResult(@RequestBody @Valid Result r) {
		return resultService.addResult(r);
	}
	
	//Result R
	@GetMapping("/result/all")
	public List<Result> getAllResult(){
		
		return resultService.getAllResult();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
