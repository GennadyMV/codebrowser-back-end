package fi.helsinki.cs.codebrowser.model;

import java.util.List;

import org.apache.commons.codec.binary.Base64;

public class Student {

    private String id;
    private String username;
    private List<Course> courses;

    public List<Course> getCourses() {

        return courses;
    }

    public void setCourses(final List<Course> courses) {

        this.courses = courses;
    }

    public String getUsername() {

        return username;
    }

    public String getId() {
        return Base64.encodeBase64URLSafeString(username.getBytes());
    }

    public String getPlainId() {

        return id;
    }

    // For Jackson deserialisation
    public void setLogin(final String login) {

        username = login;
    }

    public String getName() {

        return username;
    }
}
