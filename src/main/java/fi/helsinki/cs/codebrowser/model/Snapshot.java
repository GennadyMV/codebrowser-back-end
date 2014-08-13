package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Transient;

@Entity
public class Snapshot extends AbstractNamedPersistable implements Comparable<Snapshot> {

    @JsonIgnore
    @ManyToOne
    private ExerciseAnswer exerciseAnswer;
    private String type;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date snapshotTime;

    @OneToMany
    private List<SnapshotFile> files;

    @Transient
    private Exercise exercise;

    @Transient
    private CourseInfo course;

    private boolean compiles;

    public String getType() {

        return type;
    }

    public void setType(final String type) {

        this.type = type;
    }

    public Date getSnapshotTime() {

        return snapshotTime;
    }

    public void setSnapshotTime(final Date snapshotTime) {

        this.snapshotTime = snapshotTime;
    }

    public List<SnapshotFile> getFiles() {

        return files;
    }

    public void setFiles(final List<SnapshotFile> snapshotFiles) {

        this.files = snapshotFiles;
    }

    public ExerciseAnswer getExerciseAnswer() {

        return exerciseAnswer;
    }

    public void setExerciseAnswer(final ExerciseAnswer exerciseAnswer) {

        this.exerciseAnswer = exerciseAnswer;
    }

    public Exercise getExercise() {

        return exercise;
    }

    public void setExercise(final Exercise exercise) {

        this.exercise = exercise;
    }

    public CourseInfo getCourse() {

        return course;
    }

    public void setCourse(final CourseInfo course) {

        this.course = course;
    }

    public boolean getCompiles() {

        return compiles;
    }

    public void setCompiles(final boolean compiles) {

        this.compiles = compiles;
    }

    @Override
    public int compareTo(final Snapshot o) {

        return this.snapshotTime.compareTo(o.snapshotTime);
    }
}
