package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Tag extends AbstractPersistable<Long> {

    @Column(nullable = false)
    private String instanceId;

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String courseId;

    @Column(nullable = false)
    private String exerciseId;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    protected Tag() { }

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

    @Override
    @JsonValue
    public String toString() {

        return name;
    }
}
