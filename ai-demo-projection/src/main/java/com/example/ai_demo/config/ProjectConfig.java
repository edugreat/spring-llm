package com.example.ai_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProjectConfig {
	
	@Bean
	RestTemplate restTemplate() {
		
		return new RestTemplate();
	}

}
