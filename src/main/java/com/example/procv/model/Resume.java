package com.example.procv.model;

import java.util.List;

public class Resume {
    private String fullName;
    private String profession;
    private String phone;
    private String email;
    private String linkedin;
    private String summary;
    private List<Experience> experience;
    private List<Education> education;
    private List<String> skills;

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public List<Experience> getExperience() { return experience; }
    public void setExperience(List<Experience> experience) { this.experience = experience; }
    public List<Education> getEducation() { return education; }
    public void setEducation(List<Education> education) { this.education = education; }
    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    public static class Experience {
        private String title;
        private String company;
        private String duration;
        private String description;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getCompany() { return company; }
        public void setCompany(String company) { this.company = company; }
        public String getDuration() { return duration; }
        public void setDuration(String duration) { this.duration = duration; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public static class Education {
        private String institution;
        private String year;
        private String degree;

        public String getInstitution() { return institution; }
        public void setInstitution(String institution) { this.institution = institution; }
        public String getYear() { return year; }
        public void setYear(String year) { this.year = year; }
        public String getDegree() { return degree; }
        public void setDegree(String degree) { this.degree = degree; }
    }
}
