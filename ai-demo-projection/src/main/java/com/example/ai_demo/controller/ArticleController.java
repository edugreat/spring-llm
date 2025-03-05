package com.example.ai_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ai_demo.model.Article;
import com.example.ai_demo.service.ArticleService;



@RestController
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
//	endpoint for posting new article queries 
	@GetMapping
	public Article generateArticle(@RequestParam String seedText) {
		
		
		return articleService.generateAndSaveArticle(seedText);
	}
	
	@GetMapping("/all")
	public List<Article> getAllArticles() {
		
		return articleService.getAllArticles();
	}
	
	

}
