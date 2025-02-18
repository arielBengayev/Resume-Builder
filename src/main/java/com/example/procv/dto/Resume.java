package com.example.procv.dto;

import lombok.Getter;
import java.util.List;

@Getter
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

    @Getter
    public static class Experience {
        private String title;
        private String company;
        private String duration;
        private String description;
    }

    @Getter
    public static class Education {
        private String institution;
        private String year;
        private String degree;
    }
}
