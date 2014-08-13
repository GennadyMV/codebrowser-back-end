package fi.helsinki.cs.codebrowser.model;

import java.util.List;

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

        return id;
    }
}
