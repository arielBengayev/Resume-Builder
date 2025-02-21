package com.example.procv.service;

import com.example.procv.dto.Resume;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class DataMapperService {
    public Context setDate(Resume resume) {
        Context context = new Context();
        context.setVariable("fullName", resume.getFullName());
        context.setVariable("profession", resume.getProfession());
        context.setVariable("email", resume.getEmail());
        context.setVariable("phone", resume.getPhone());
        context.setVariable("linkedin", resume.getLinkedin());
        context.setVariable("summary", resume.getSummary());
        context.setVariable("skills", resume.getSkills());
        context.setVariable("experience", resume.getExperience());
        context.setVariable("education", resume.getEducation());
        return context;
    }
}
