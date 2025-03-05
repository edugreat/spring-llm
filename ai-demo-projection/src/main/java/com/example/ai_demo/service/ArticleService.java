package com.example.ai_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ai_demo.model.Article;
import com.example.ai_demo.repo.ArticleRepository;

@Service
public class ArticleService {
	@Autowired
	private ChatGptService chatGptService;
	
	@Autowired
	private ArticleRepository articleRepo;
	
	@Transactional
	public Article generateAndSaveArticle(String seedText) {
		
		
		String generatedContent = chatGptService.generateText(seedText);
		
		if(generatedContent.length() > 255) {
			
			generatedContent = generatedContent.substring(0, 250);
		}
//	shorten the query title
		String generatedTitle = "Generated Title: "+seedText.substring(0, Math.min(10, seedText.length()))+"...";
		
	   Article article = new Article(generatedTitle, generatedContent);
	   
	   return articleRepo.save(article);
	
	}
	
//	List all generated articles
	public List<Article> getAllArticles(){
		
		return articleRepo.findAll();
	}

}
