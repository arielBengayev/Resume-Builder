package com.example.procv.controller;

import com.example.procv.dto.ChatRequest;
import com.example.procv.service.PdfResumeService;
import com.example.procv.service.ProcessedHtmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/bot")
public class AppController {
    @Autowired
    private ProcessedHtmlService processedHtml;

    @Autowired
    private PdfResumeService pdfResume;

    @PostMapping("/generateResume")
    public ResponseEntity<byte[]> generateResume(@RequestBody ChatRequest request) throws IOException {
        String resumeContent = processedHtml.getResumeContent(request);
        String finalHtml = processedHtml.finalHtml(resumeContent, request.getStyle());
        return pdfResume.getPdfResponse(finalHtml);
    }
}