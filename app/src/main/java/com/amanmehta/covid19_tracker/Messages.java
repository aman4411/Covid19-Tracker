package com.amanmehta.covid19_tracker;

public class Messages {
    private String email,experience;

    public Messages() {
    }

    public Messages(String email, String experience) {
        this.email = email;
        this.experience = experience;
    }

    public String getEmail() {
        return email;
    }

    public String getExperience() {
        return experience;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
