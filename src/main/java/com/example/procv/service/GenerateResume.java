package com.example.procv.service;

import com.example.procv.dto.Resume;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class GenerateResume {
    @Autowired
    private SpringTemplateEngine springTemplateEngine;
    @Autowired
    private DataMapper dataMapper;
    @Autowired
    private ObjectMapper objectMapper;

    public String processedHtml(String resumeContent, String style) throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Resume resume = objectMapper.readValue(resumeContent, Resume.class);
        Context context = dataMapper.setDate(resume);
        String finalHtml = springTemplateEngine.process(style, context);
        return finalHtml;
    }
    public byte[] generatePdf(String processedHtml) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
           ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResponseEntity<byte[]> getPdfResponse(String processedHtml) throws IOException {
        byte[] pdfBytes = generatePdf(processedHtml);

        if (pdfBytes == null || pdfBytes.length == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline().filename("resume.pdf").build());

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}
