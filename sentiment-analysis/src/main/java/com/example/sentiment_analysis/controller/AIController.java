package com.example.sentiment_analysis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sentiment_analysis.service.ChatGptService;

import reactor.core.publisher.Mono;


@RestController
public class AIController {
	
	@Autowired
	private  ChatGptService chatGptService;
	
	@GetMapping("/analyse")
	public Mono<List<String>> chat(@RequestParam String userResponse) {
		
		return chatGptService.getChatResponse(userResponse);
	}
	

}
