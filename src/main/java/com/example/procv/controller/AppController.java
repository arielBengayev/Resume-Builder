package com.example.procv.controller;

import com.example.procv.dto.ChatRequestDTO;
import com.example.procv.openAI.ChatGPTRequest;
import com.example.procv.openAI.ChatGPTResponse;
import com.example.procv.service.GenerateResume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;

@RestController
@RequestMapping("/api/bot")
public class AppController {
    @Value("${openai.model}")
    private String openAImodel;
    @Value("${openai.api.url}")
    private String url;
    @Autowired
    private RestTemplate template;
    @Autowired
    private GenerateResume generateResume;

    @PostMapping("/generateResume")
    public ResponseEntity<byte[]> generateResume(@RequestBody ChatRequestDTO requestDTO) throws IOException {
        ChatGPTRequest chatGPTRequest = new ChatGPTRequest(openAImodel, requestDTO.getPrompt());
        ChatGPTResponse chatGPTResponse = template.postForObject(url, chatGPTRequest, ChatGPTResponse.class);
        String resumeContent = chatGPTResponse.getChoices().get(0).getMessage().getContent();
        String processedHtml = generateResume.processedHtml(resumeContent, requestDTO.getStyle());

        return generateResume.getPdfResponse(processedHtml);
    }
}