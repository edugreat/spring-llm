package com.example.sentiment_analysis.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.ai.inference.ChatCompletionsClient;
import com.azure.ai.inference.ChatCompletionsClientBuilder;
import com.azure.ai.inference.models.ChatCompletionsOptions;
import com.azure.ai.inference.models.ChatRequestMessage;
import com.azure.ai.inference.models.ChatRequestSystemMessage;
import com.azure.ai.inference.models.ChatRequestUserMessage;
import com.azure.core.credential.AzureKeyCredential;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class ChatGptService {
	
	private final String API_KEY = System.getenv("GITHUB_TOKEN");
	
	@Value("${azure.ai.endpoint}")
	private  String AZURE_ENDPOINT;
	
	 String model = "gpt-4o";
	
	 public Mono<List<String>> getChatResponse(String userMessage) {
	        // Build the Azure AI client
	        ChatCompletionsClient client = new ChatCompletionsClientBuilder()
	                .credential(new AzureKeyCredential(API_KEY))
	                .endpoint(AZURE_ENDPOINT)
	                .buildClient();

	        // Prepare the chat messages
	        List<ChatRequestMessage> chatMessages = Arrays.asList(
	                new ChatRequestSystemMessage("You are a sentiment analyst."),
	                new ChatRequestUserMessage(userMessage)
	        );

	        // Configure the chat completion options
	        ChatCompletionsOptions chatCompletionsOptions = new ChatCompletionsOptions(chatMessages);
	        chatCompletionsOptions.setModel(model);

	        // Execute the blocking call on a separate thread pool
	        return Mono.fromCallable(() -> client.complete(chatCompletionsOptions))
	                .subscribeOn(Schedulers.boundedElastic()) // Use boundedElastic for blocking calls
	                .map(completions -> completions.getChoices()
	                .stream().map(completion -> completion.getMessage().getContent())
	                .collect(Collectors.toList()));
	                
	                       
	    }

}
