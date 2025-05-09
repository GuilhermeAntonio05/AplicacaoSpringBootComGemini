package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.QnAnswerService;

@RestController
public class TesteController {

	@Autowired
	private QnAnswerService qnAnswer;
	
	@GetMapping("/ai")
	public String heloAtualizado() {
		return "Hello com ia!";
	}
	
	@PostMapping("/ai")
	public ResponseEntity<String> helo(@RequestBody Map<String, String> payload) {
		String question = payload.get("question");
		String answer = qnAnswer.getAnswer(question);
		return ResponseEntity.ok(answer);
	}
	
}
