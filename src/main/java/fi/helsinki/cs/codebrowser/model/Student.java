package fi.helsinki.cs.codebrowser.model;

import java.util.List;

import javax.persistence.ManyToMany;

//@Entity
public class Student {

    private String id;
    private String username;

    @ManyToMany
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

    public void setUsername(final String username) {

        this.username = username;
    }

    public String getId() {

        return id;
    }
}
