package com.example.ai_demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ai_demo.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
