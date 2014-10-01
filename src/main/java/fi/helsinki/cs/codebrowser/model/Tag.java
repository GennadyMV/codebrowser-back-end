package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Tag extends AbstractPersistable<Long> {

    @Column(nullable = false)
    @JsonIgnore
    private String instanceId;

    @Column(nullable = false)
    @JsonIgnore
    private String studentId;

    @Column(nullable = false)
    @JsonIgnore
    private String courseId;

    @Column(nullable = false)
    @JsonIgnore
    private String exerciseId;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @ManyToOne
    @JsonIgnore
    private User user;

    public void setInstanceId(final String instanceId) {

        this.instanceId = instanceId;
    }

    public String getInstanceId() {

        return instanceId;
    }

    public void setStudentId(final String studentId) {

        this.studentId = studentId;
    }

    public String getStudentId() {

        return studentId;
    }

    public void setCourseId(final String courseId) {

        this.courseId = courseId;
    }

    public String getCourseId() {

        return courseId;
    }

    public void setExerciseId(final String exerciseId) {

        this.exerciseId = exerciseId;
    }

    public String getExerciseId() {

        return exerciseId;
    }

    public void setName(final String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setUser(final User user) {

        this.user = user;
    }

    public User getUser() {

        return this.user;
    }
}
