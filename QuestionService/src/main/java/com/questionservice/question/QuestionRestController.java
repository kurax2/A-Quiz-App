package com.questionservice.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/question")
@Validated
public class QuestionRestController {
	
	@Autowired
	QuestionService questionService;

	public QuestionRestController() {
		System.out.println("----->> Inside Question Cotroller Constructor ...");
	}
	
	//QUESTION r
	@GetMapping("all")
	public List<Question> getAllQuestions()
	{
		return questionService.getAllQuestions();
	}
	
	@GetMapping("all/cat/{category}")
	public List<Question> getAllQuestionsByCat(@PathVariable String category)
	{
		return questionService.getQuestionByCat(category);
	}

	
	
}
