package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import org.apache.commons.codec.binary.Base64;

public final class Student {

    private String id;
    private String username;
    private List<Course> courses;

    public void setPlainId(final String id) {

        this.id = id;
    }

    @JsonIgnore
    public String getPlainId() {

        return id;
    }

    public String getId() {

        return Base64.encodeBase64URLSafeString(username.getBytes());
    }

    public void setUsername(final String username) {

        this.username = username;
    }

    // For Jackson deserialisation from TMC
    public void setLogin(final String login) {

        setUsername(login);
    }

    public String getUsername() {

        return username;
    }

    public String getName() {

        return username;
    }

    public void setCourses(final List<Course> courses) {

        this.courses = courses;
    }

    public List<Course> getCourses() {

        return courses;
    }
}
