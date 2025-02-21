package com.example.procv.service;

import com.example.procv.dto.ChatRequest;
import com.example.procv.dto.Resume;
import com.example.procv.openAI.ChatGPTRequest;
import com.example.procv.openAI.ChatGPTResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class ProcessedHtmlService {
    @Value("${openai.model}")
    private String openAImodel;

    @Value("${openai.api.url}")
    private String url;

    @Autowired
    private RestTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DataMapperService dataMapper;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    public String getResumeContent(ChatRequest request){
        ChatGPTRequest chatGPTRequest = new ChatGPTRequest(openAImodel, request.getPrompt());
        ChatGPTResponse chatGPTResponse = template.postForObject(url, chatGPTRequest, ChatGPTResponse.class);
        String resumeContent = chatGPTResponse.getChoices().get(0).getMessage().getContent();
        return resumeContent;
    }

    public String finalHtml(String resumeContent, String style) throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Resume resume = objectMapper.readValue(resumeContent, Resume.class);
        Context context = dataMapper.setDate(resume);
        String finalHtml = springTemplateEngine.process(style, context);
        return finalHtml;
    }
}
