package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class QnAnswerService {

	  private final String API_LINK = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=";
	    private final String API_VALUE = "Api_key"; // Substitua com sua chave de API real
	    private final String FULL_URL = API_LINK + API_VALUE; // Combine a URL base e a chave

	    private final WebClient webClient;

	    public QnAnswerService(WebClient.Builder webBuilder) {
	        this.webClient = webBuilder.build();
	    }

	    public String getAnswer(String question) {
	        // Construa a estrutura correta do corpo da requisição
	        List<Map<String, Object>> contents = List.of(
	                Map.of("parts", List.of(Map.of("text", question)))
	        );
	        Map<String, Object> requestBody = Map.of("contents", contents);


	        String response = webClient.post()
	                .uri(FULL_URL) // Use a URL completa
	                .header("Content-Type", "application/json")
	                .bodyValue(requestBody)
	                .retrieve()
	                .bodyToMono(String.class)
	                .block();

	        return response;
	}

}
