package com.example.procv.controller;

import com.example.procv.dto.ChatRequestDTO;
import com.example.procv.openAI.ChatGPTRequest;
import com.example.procv.openAI.ChatGPTResponse;
import com.example.procv.service.HtmlToPdf;
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
    private HtmlToPdf htmlToPdf;

    @PostMapping("/chat")
    public ResponseEntity<byte[]> generateResume(@RequestBody ChatRequestDTO requestDTO) throws IOException {
        String prompt = requestDTO.getPrompt();
        String style = requestDTO.getStyle();

        ChatGPTRequest request = new ChatGPTRequest(openAImodel, prompt);
        ChatGPTResponse chatGPTResponse = template.postForObject(url, request, ChatGPTResponse.class);

        String resumeContent = chatGPTResponse.getChoices().get(0).getMessage().getContent();
        String processedHtml = htmlToPdf.processedHtml(resumeContent, style);
        byte[] pdfBytes = htmlToPdf.convert(processedHtml);

        if (pdfBytes == null || pdfBytes.length == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline().filename("resume.pdf").build());

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
