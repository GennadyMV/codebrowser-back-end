package fi.helsinki.cs.codebrowser.model;

import java.util.Date;
import java.util.List;

public class Snapshot implements Comparable<Snapshot> {

    private String id;
    private String type;

    private Date snapshotTime;
    private List<SnapshotFile> files;
    private Exercise exercise;
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

    public String getId() {

        return id;
    }

    @Override
    public int compareTo(final Snapshot o) {

        return this.snapshotTime.compareTo(o.snapshotTime);
    }
}
