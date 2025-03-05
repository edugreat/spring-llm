package com.example.ai_demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.ChatCompletions;
import com.azure.ai.inference.models.ChatCompletionsOptions;
import com.azure.ai.inference.models.ChatRequestMessage;
import com.azure.ai.inference.models.ChatRequestSystemMessage;
import com.azure.ai.inference.models.ChatRequestUserMessage;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.Configuration;

@Service
public class ChatGptService {
	
//	@Autowired
//	private RestTemplate restTemplate;
	@Value("${openai.api.url}")
	private String apiUrl;
	
	private final String API_KEY  = System.getenv("GITHUB_TOKEN");//get the api_key environment variable
	
	//private final String KEY = Configuration.getGlobalConfiguration().get("GITHUB_TOKEN");
	public String generateText(String prompt) {
	
	
//	set up the AI model
	String model = "gpt-4o";
	
//	configure your credential and query end point
	ChatCompletionsClient client = new ChatCompletionsClientBuilder()
            .credential(new AzureKeyCredential(API_KEY))
            .endpoint(apiUrl)
            .buildClient();

//	configure your query
        List<ChatRequestMessage> chatMessages = Arrays.asList(
            new ChatRequestSystemMessage("You are a helpful news article assistant."),
            new ChatRequestUserMessage(prompt)
        );

        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
        chatCompletionsOptions.setModel(model);

        ChatCompletions completions = client.complete(chatCompletionsOptions);
        
//        return the AI provided choice or answer
	return completions.getChoices().get(0).getMessage().getContent();
	
	
	}
}