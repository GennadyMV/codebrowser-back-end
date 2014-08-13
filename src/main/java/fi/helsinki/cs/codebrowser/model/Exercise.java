package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
        return id;
    }

    public String getName() {
        return name;
    }
}
