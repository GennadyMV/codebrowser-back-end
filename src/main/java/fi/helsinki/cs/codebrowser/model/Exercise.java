package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Exercise extends AbstractNamedPersistable {

    @JsonIgnore
    @ManyToOne
    private Course course;

    public Course getCourse() {

        return course;
    }

    public void setCourse(final Course course) {

        this.course = course;
    }
}
