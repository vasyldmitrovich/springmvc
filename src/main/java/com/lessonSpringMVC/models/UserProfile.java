package com.lessonSpringMVC.models;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserProfile {
    private long userId;
    private String name;

    //TODO Add that and another field when will building DB, generate new constructor getter and another things

    /* private String surname;
     * private int age;
     * private int height;
     * private int weight;
     * private String photo;*/

    public UserProfile() {
    }

    public UserProfile(long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return userId == that.userId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name);
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
