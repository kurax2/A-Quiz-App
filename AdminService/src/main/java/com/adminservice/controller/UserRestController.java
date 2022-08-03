package com.adminservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.adminservice.admin.User;
import com.adminservice.admin.UserService;
import com.adminservice.question.Question;
import com.adminservice.question.QuestionService;
import com.adminservice.result.ResultService;
import com.adminservice.result.Result;

@RestController
@CrossOrigin(origins= {"http://localhost:8082", "http://localhost:4200"},allowedHeaders = "*")
@RequestMapping("/abc-university/admin")
@Validated
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	ResultService resultService;
	
	@Autowired
	RestTemplate restTemplate;

	public UserRestController() {
		
		System.out.println("User Controller constructor called");
	}
		//--------------------------------------------------------------------------------Validate-Token-Function----------------------------------------------------------------------------------------------------
	
		public boolean validateToken(HttpServletRequest request) {
		
		org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "ncs-"+request.getHeader("Authorization"));
		headers.set("userType","admin");
		//headers.set("Authority", request.getHeader("Authority"));

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		RestTemplate r =new RestTemplate();
		ResponseEntity<Boolean> tokenTrue=r.exchange("http://localhost:8081/abc-university/public/validate", HttpMethod.GET,entity,Boolean.class);
	
		return tokenTrue.getBody().booleanValue();
		}
		
		//--------------------------------------------------------------------------------USER-------------------------------------------------------------------------------------------------------------
		
		//Add
		@PostMapping("/user/add")
		public ResponseEntity<User> addUser(@RequestBody @Valid User u,HttpServletRequest request)
		{
			//boolean tokenValid=validateToken(request);
			User savedUser =new User();
			
			//if(tokenValid) {
				savedUser = userService.addUser(u);
			//}
			
			return new ResponseEntity<User>(savedUser,HttpStatus.OK);
			
		}
		//Get All
		@GetMapping("/user/all")
		public List<User> getAllUsers(HttpServletRequest request)
		{
			//boolean tokenValid=validateToken(request);
			List<User> list = new ArrayList<>();
			
			//if(tokenValid) {
			list=userService.getAllUsers();
			//}
			return list;
		}
		
		//Update
		@PutMapping("/user/update")
		public ResponseEntity<User> updateProject(@RequestBody User u, HttpServletRequest request)
		{	
			//boolean tokenValid=validateToken(request);
			
			User updateUser = u;
			
			//if(tokenValid) {
			userService.updateUsers(updateUser);
			//}
			
			return new ResponseEntity<>(updateUser,HttpStatus.OK);
		}
		
		
		//Delete
		@DeleteMapping("/user/delete/{userId}")
		public void deleteUser(@PathVariable int userId, HttpServletRequest request)
		{
			//boolean tokenValid=validateToken(request);
	
			//if(tokenValid) {
				userService.deleteUserByID(userId);
				
			//}
			
		}
		
		//--------------------------------------------------------------------------------QUESTION----------------------------------------------------------------------------------------------------
		
		//QUESTION c
		@PostMapping("/question/add")
		public ResponseEntity<Question> addQuestion(@RequestBody @Valid Question q, HttpServletRequest request)
		{
			boolean tokenValid=validateToken(request);
			Question savedQuestion =new Question();
			
			if(tokenValid) {
				savedQuestion = restTemplate.postForObject("http://QUESTION-SERVICE/abc-university/question/add", q,Question.class);
			}
			return new ResponseEntity<Question>(savedQuestion,HttpStatus.OK);
			
		}
		//QUESTION r
		@GetMapping("/question/all")
		public List<Question> getAllQuestions(HttpServletRequest request)
		{	
			//boolean tokenValid=validateToken(request);
			
			List<Question> q = new ArrayList<>();
			
			//if(tokenValid) {
				q = restTemplate.getForObject("http://QUESTION-SERVICE/abc-university/question/all",List.class);
			//}
			return q;
		}
		
		//QUESTION u
		@PutMapping("/question/update")
		public ResponseEntity<Question> updateQuestion(@RequestBody Question q,HttpServletRequest request)
		{
			
			//boolean tokenValid=validateToken(request);
			
			Question updatedQuestion = new Question();
			
			//if(tokenValid) {
				
				restTemplate.put("http://QUESTION-SERVICE/abc-university/question/update", q, Question.class);
				updatedQuestion=q;
			//}
			return new ResponseEntity<Question>(updatedQuestion,HttpStatus.OK);
		}
		
		
		//QUESTION d
		@DeleteMapping("/question/delete/{questionId}")
		public void deleteQuestion(@PathVariable int questionId,HttpServletRequest request)
		{
			//boolean tokenValid=validateToken(request);
			
			//if(tokenValid) {
			restTemplate.delete("http://QUESTION-SERVICE/abc-university/question/delete/"+questionId,Question.class);
			//}
			
		}
		
		////--------------------------------------------------------------------------------RESULT----------------------------------------------------------------------------------------------------
		
		//RESULT c
		@PostMapping("/result/add")
		public Result addResult(@RequestBody @Valid Result r,HttpServletRequest request) {
			boolean tokenValid=validateToken(request);
			
			Result savedResult =new Result();
			
			if(tokenValid) {
				savedResult = restTemplate.postForObject("http://RESULT-SERVICE/abc-university/result/add", r,Result.class);
				
			}
			return savedResult;
		}
		
		//RESULT r
		@GetMapping("/result/all")
		public List<Result> getAllResult(HttpServletRequest request){
			
			//boolean tokenValid=validateToken(request);
			
			List<Result> r = new ArrayList<>();
			
			//if(tokenValid) {
				r = restTemplate.getForObject("http://RESULT-SERVICE/abc-university/result/all", List.class);
			//}
			return r;
		}
		
		//RESULT u
		@PutMapping("/result/update")
		public Result updateResult(@RequestBody Result r,HttpServletRequest request) {
			
			boolean tokenValid=validateToken(request);
			
			Result updatedResult = new Result();
			
			if(tokenValid) {
				
				restTemplate.put("http://RESULT-SERVICE/abc-university/result/update", updatedResult, Result.class);
				updatedResult = r;
			}
			
			return(updatedResult);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	

}
