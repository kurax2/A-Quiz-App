package com.quizapp.server.QuizAppServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class QuizAppServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizAppServerApplication.class, args);
	}

}
