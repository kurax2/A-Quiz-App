package com.adminservice.controller;

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
import org.springframework.web.client.RestTemplate;

import com.adminservice.admin.User;
import com.adminservice.admin.UserService;
import com.adminservice.question.Question;
import com.adminservice.question.QuestionService;
import com.adminservice.result.ResultService;
import com.adminservice.result.Result;



@RestController
@RequestMapping("/api/admin")
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
		
		//--------------------------------------------------------------------------------USER----------------------------------------------------------------------------------------------------
		
		//USER c
		@PostMapping("/user/add")
		public ResponseEntity<User> addUser(@RequestBody @Valid User u)
		{
			
			User savedUser = userService.addUser(u);
			
			return new ResponseEntity<User>(savedUser,HttpStatus.OK);
			
		}
		//USER r
		@GetMapping("/user/all")
		public List<User> getAllUsers()
		{
			return userService.getAllUsers();
		}
		
		//USER u
		@PutMapping("/user/update")
		public ResponseEntity<User> updateProject(@RequestBody User u)
		{
			User updateUser = userService.updateUsers(u);
			return new ResponseEntity<>(updateUser,HttpStatus.OK);
		}
		
		
		//USER d
		@DeleteMapping("/user/delete/{userId}")
		public void deleteUser(@PathVariable int userId)
		{
			userService.deleteUserByID(userId);
			
		}
		
		//--------------------------------------------------------------------------------QUESTION----------------------------------------------------------------------------------------------------
		
		//QUESTION c
		@PostMapping("/question/add")
		public ResponseEntity<Question> addQuestion(@RequestBody @Valid Question u)
		{
			
			Question savedQuestion = questionService.addQuestion(u);
			
			return new ResponseEntity<Question>(savedQuestion,HttpStatus.OK);
			
		}
		//QUESTION r
		@GetMapping("/question/all")
		public List<Question> getAllQuestions()
		{
			List<Question> q = restTemplate.getForObject("http://QUESTION-SERVICE/api/question/all/",List.class);
			return q;
		}
		
		//QUESTION u
		@PutMapping("/question/update")
		public ResponseEntity<Question> updateQuestion(@RequestBody Question u)
		{
			Question updateQuestion= questionService.updateQuestions(u);
			return new ResponseEntity<>(updateQuestion,HttpStatus.OK);
		}
		
		
		//QUESTION d
		@DeleteMapping("/question/delete/{questionId}")
		public void deleteQuestion(@PathVariable int questionId)
		{
			questionService.deleteQuestionByID(questionId);
			
		}
		
		////--------------------------------------------------------------------------------RESULT----------------------------------------------------------------------------------------------------
		
		//RESULT c
		@PostMapping("/result/add")
		public Result addResult(@RequestBody @Valid Result r) {
			Result q = restTemplate.postForObject("http://RESULT-SERVICE/api/result/add", r,Result.class);
			return q;
		}
		
		//RESULT r
		@GetMapping("/result/all")
		public List<Result> getAllResult(){
			
			List<Result> r = restTemplate.getForObject("http://RESULT-SERVICE/api/result/all", List.class);
			return r;
		}
		
		//RESULT u
		@PutMapping("/result/update")
		public Result updateResult(@RequestBody Result r) {
			
			return resultService.updateResult(r);
		}
		
		//RESULT d
		@DeleteMapping("/result/delete/{resultId}")
		public void deleteResult(@PathVariable int resultId){
			resultService.deleteResultById(resultId);
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	

}
