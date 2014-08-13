package fi.helsinki.cs.codebrowser.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Student extends AbstractNamedPersistable {

    @ManyToMany
    private List<Course> courses;

    public List<Course> getCourses() {

        return courses;
    }

    public void setCourses(final List<Course> courses) {

        this.courses = courses;
    }
}
