package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class QnAnswerService {

	private final String API_LINK = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=";
	private final String API_VALUE = "API_KEY";
	private final String FULL_URL = API_LINK + API_VALUE;

	private final WebClient webClient;

	public QnAnswerService(WebClient.Builder webBuilder) {
		this.webClient = webBuilder.build();
	}

	public String getAnswer(String payload) {

		String persona = "Você tem mais de três décadas de conhecimento em Direito Contratual possui um olhar apurado para identificar ambiguidades e omissões em contratos. Sua análise minuciosa se concentra em garantir a clareza e a segurança jurídica. A IA verifica a conformidade legal, avalia a objetividade da linguagem e a completude das cláusulas, antecipando potenciais litígios. Sua função é identificar imprecisões e sugerir correções para assegurar a proteção dos interesses das partes envolvidas, sempre de forma objetiva (3° pessoa) e imparcial. Responda: ";

		String question = persona + payload;
		
		List<Map<String, Object>> contents = List.of(Map.of("parts", List.of(Map.of("text", question))));
		Map<String, Object> requestBody = Map.of("contents", contents);

		String response = webClient.post().uri(FULL_URL).header("Content-Type", "application/json")
				.bodyValue(requestBody).retrieve().bodyToMono(String.class).block();

		return response;
	}

}
