package com.questionservice.controller;

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

import com.questionservice.question.Question;
import com.questionservice.question.QuestionService;

@RestController
@RequestMapping("/abc-university/question")
@Validated
public class QuestionRestController {
	
	@Autowired
	QuestionService questionService;

	public QuestionRestController() {
		System.out.println("----->> Inside Question Cotroller Constructor ...");
	}
	
	//QUESTION c
	@PostMapping("/add")
	public ResponseEntity<Question> addQuestion(@RequestBody @Valid Question u)
	{
			
		Question savedQuestion = questionService.addQuestion(u);
				
		return new ResponseEntity<Question>(savedQuestion,HttpStatus.OK);
			
	}
			
	//QUESTION r
	@GetMapping("/all")
	public List<Question> getAllQuestions()
	{
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/all/cat/{category}")
	public List<Question> getAllQuestionsByCat(@PathVariable String category)
	{
		return questionService.getQuestionByCat(category);
	}
	
	//QUESTION u
	@PutMapping("/update")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question u)
	{
		Question updateQuestion= questionService.updateQuestions(u);
				return new ResponseEntity<>(updateQuestion,HttpStatus.OK);
	}
			
			
	//QUESTION d
	@DeleteMapping("/delete/{questionId}")
	public void deleteQuestion(@PathVariable int questionId)
	{
		questionService.deleteQuestionByID(questionId);
				
	}
	
	

	
	
}
