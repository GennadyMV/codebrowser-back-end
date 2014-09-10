package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

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

    @Column(nullable = false)
    private String snapshotId;

    @Pattern(regexp = "key|code")
    @Column(name = "code_level", nullable = false)
    private String level;

    @Column(nullable = false)
    private String name;

    protected Tag() {
    }

    public void setInstanceId(final String instanceId) {
        this.instanceId = instanceId;
    }

    public void setStudentId(final String studentId) {

        this.studentId = studentId;
    }

    public void setCourseId(final String courseId) {

        this.courseId = courseId;
    }

    public void setExerciseId(final String exerciseId) {

        this.exerciseId = exerciseId;
    }

    public void setLevel(final String level) {

        this.level = level;
    }

    public void setSnapshotId(final String snapshotId) {

        this.snapshotId = snapshotId;
    }

    public void setName(final String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    @JsonValue
    @Override
    public String toString() {

        return name;
    }
}
