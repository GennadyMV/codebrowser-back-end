package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.apache.tomcat.util.codec.binary.Base64;

public class Exercise {

    private String id;
    private String name;

    @JsonIgnore
    private Course course;

    public Course getCourse() {

        return course;
    }

    public void setCourse(final Course course) {

        this.course = course;
    }

    public String getId() {

        return Base64.encodeBase64URLSafeString(name.getBytes());
    }

    public String getPlainId() {

        return id;
    }

    public void setPlainId(final String id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(final String name) {

        this.name = name;
    }
}
