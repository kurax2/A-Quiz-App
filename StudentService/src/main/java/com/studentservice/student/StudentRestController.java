package com.studentservice.student;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	
	//--------------------------------------------------------------------------------Validate-Token-Function----------------------------------------------------------------------------------------------------
	
			public boolean validateToken(HttpServletRequest request) {
			
			org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
			
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "ncs-"+request.getHeader("Authorization"));
			headers.set("userType","student");
			//headers.set("Authority", request.getHeader("Authority"));

			HttpEntity<String> entity = new HttpEntity<String>(headers);
			
			RestTemplate r =new RestTemplate();
			ResponseEntity<Boolean> tokenTrue=r.exchange("http://localhost:8081/abc-university/public/validate", HttpMethod.GET,entity,Boolean.class);
		
			return tokenTrue.getBody().booleanValue();
			}
			
	//--------------------------------------------------------------------------------QUESTION------------------------------------------------------------------------------------------------------------
	
	//Get All
	@GetMapping("/question/all")
	public List<Question> getAllQuestion(HttpServletRequest request){
		
		boolean tokenValid=validateToken(request);
		
		List<Question> list = new ArrayList<>();
		
		
		if(tokenValid) {
			list=restTemplate.getForObject("http://QUESTION-SERVICE/abc-university/question/all/",List.class);
		}
		return list;
			
	}
	
	//Get All by Category
	@GetMapping("/question/all/{category}")
	public List<Question> getAllQuestionByCat(@PathVariable String category,HttpServletRequest request){
		
		boolean tokenValid=validateToken(request);
		
		List<Question> list = new ArrayList<>();
		
		
		if(tokenValid) {
			list = restTemplate.getForObject("http://QUESTION-SERVICE/abc-university/question/all/cat/"+category,List.class);
		}
		
		return list;
	}
	
	//--------------------------------------------------------------------------------RESULT-------------------------------------------------------------------------------------------------------------
	
	//Add
	@PostMapping("/result/add")
	public Result addResult(@RequestBody @Valid Result r,HttpServletRequest request) {
		
		boolean tokenValid=validateToken(request);
		
		Result savedResult =new Result();
		
		if(tokenValid) {
			savedResult = restTemplate.postForObject("http://RESULT-SERVICE/abc-university/result/add", r,Result.class);
		}
		
		return savedResult;
	}
	
	//Get All
	@GetMapping("/result/all")
	public List<Result> getAllResult(HttpServletRequest request){
		
		boolean tokenValid=validateToken(request);
		
		List<Result> r = new ArrayList<>();
		
		if(tokenValid) {
			 r = restTemplate.getForObject("http://RESULT-SERVICE/abc-university/result/all", List.class);
		}
		return r;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
