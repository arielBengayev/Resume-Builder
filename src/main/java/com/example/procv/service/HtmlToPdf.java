package com.example.procv.service;

import com.example.procv.model.Resume;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class HtmlToPdf {
    @Autowired
    private SpringTemplateEngine springTemplateEngine;
    @Autowired
    private DataMapper dataMapper;

    public String processedHtml(String resumeContent, String style) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Resume resume = om.readValue(resumeContent, Resume.class);
        Context context = dataMapper.setDate(resume);
        String finalHtml = springTemplateEngine.process(style, context);
        return finalHtml;
    }
    public byte[] convert(String processedHtml) throws IOException {
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
}