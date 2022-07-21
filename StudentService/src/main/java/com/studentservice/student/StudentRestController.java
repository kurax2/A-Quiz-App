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
import org.springframework.web.client.RestTemplate;

import com.studentservice.question.Question;
import com.studentservice.question.QuestionRepo;
import com.studentservice.question.QuestionService;
import com.studentservice.result.Result;
import com.studentservice.result.ResultService;

@RestController
@RequestMapping("/abc-university/student")
@Validated
public class StudentRestController {
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuestionRepo questionRepo;
	
	@Autowired
	ResultService resultService;
	
	@Autowired
	RestTemplate restTemplate;

	public StudentRestController() {
		
		System.out.println("Student Controller constructor called");
	}
	//--------------------------------------------------------------------------------QUESTION----------------------------------------------------------------------------------------------------
	
	//Get All
	@GetMapping("/question/all")
	public List<Question> getAllQuestion(){
		List<Question> q = restTemplate.getForObject("http://QUESTION-SERVICE/abc-university/question/all/",List.class);
		return q;
			
	}
	
	//Get All by Category
	@GetMapping("/question/all/{category}")
	public List<Question> getAllQuestionByCat(@PathVariable String category){
		List<Question> q = restTemplate.getForObject("http://QUESTION-SERVICE/abc-university/question/all/cat/"+category,List.class);
		return q;
	}
	
	//--------------------------------------------------------------------------------RESULT----------------------------------------------------------------------------------------------------
	
	//Add
	@PostMapping("/result/add")
	public Result addResult(@RequestBody @Valid Result r) {
		Result q = restTemplate.postForObject("http://RESULT-SERVICE/abc-university/result/add", r,Result.class);
		return q;
	}
	
	//Get All
	@GetMapping("/result/all")
	public List<Result> getAllResult(){
		
		List<Result> r = restTemplate.getForObject("http://RESULT-SERVICE/abc-university/result/all", List.class);
		return r;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
