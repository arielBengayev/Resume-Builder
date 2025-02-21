package com.example.procv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Experience {
        private String title;
        private String company;
        private String duration;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Education {
        private String institution;
        private String year;
        private String degree;
    }
}
