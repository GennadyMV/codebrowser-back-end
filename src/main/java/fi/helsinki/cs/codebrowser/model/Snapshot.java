package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import difflib.DiffUtils;
import difflib.Patch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
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

    @OneToMany
    private List<Testresult> tests;

    private boolean compiles;
    private Integer percentageOfTestsPassing;

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

    public static List<Snapshot> filterSequentialUnalteredSnapshots(final List<Snapshot> snapshots) {

        if (snapshots == null || snapshots.size() <= 1) {
            return snapshots;
        }

        Collections.sort(snapshots);

        final List<Snapshot> filteredSnapshots = new ArrayList<>();
        filteredSnapshots.add(snapshots.get(0));


        List<String> snapshotLines = filesToLines(snapshots.get(0).getFiles());

        if (snapshotsAreEqual(snapshotLines, filesToLines(snapshots.get(snapshots.size() - 1).getFiles()))) {
            snapshots.remove(snapshots.size() - 1);
        }

        for (int i = 1; i < snapshots.size(); i++) {
            final Snapshot current = snapshots.get(i);
            final List<String> currentLines = filesToLines(current.getFiles());

            if (snapshotsAreEqual(snapshotLines, currentLines)) {
                continue;
            }

            filteredSnapshots.add(current);
            snapshotLines = currentLines;
        }

        return filteredSnapshots;
    }

    private static boolean snapshotsAreEqual(final List<String> snapshotLines, final List<String> currentLines) {

        if (snapshotLines.size() != currentLines.size()) {
            return false;
        }

        final Patch diff = DiffUtils.diff(snapshotLines, currentLines);

        if (diff == null || diff.getDeltas() == null || diff.getDeltas().isEmpty()) {
            return true;
        }

        for (int i = 0; i < currentLines.size(); i++) {
            if (currentLines.get(i).contains("/home/group")) {
                // ignore file location
                continue;
            }

            if (!currentLines.get(i).trim().equals(snapshotLines.get(i).trim())) {
                return false;
            }
        }

        return true;
    }

    private static List<String> filesToLines(final List<SnapshotFile> files) {

        Collections.sort(files, new Comparator<SnapshotFile>() {
            @Override
            public int compare(final SnapshotFile o1, final SnapshotFile o2) {
                return o1.getFilepath().compareTo(o2.getFilepath());
            }
        });

        final List<String> lines = new ArrayList<>();
        for (SnapshotFile file : files) {
//            lines.add(file.getFilepath());
            lines.addAll(fileToLines(file.getFilepath()));
        }

        return lines;
    }

    private static List<String> fileToLines(final String filename) {

        final List<String> lines = new LinkedList<>();
        String line;

        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return lines;
    }

    public Integer getPercentageOfTestsPassing() {

        //countPercentage();
        return percentageOfTestsPassing;
    }

    public void setPercentageOfTestsPassing(final Integer percentageOfTestsPassing) {

        this.percentageOfTestsPassing = percentageOfTestsPassing;
    }

    public List<Testresult> getTests() {

        return tests;
    }

    public void setTests(final List<Testresult> tests) {

        this.tests = tests;
        countPercentage();
    }

    private void countPercentage() {

        if (tests.isEmpty()) {
            this.setPercentageOfTestsPassing(null);
            return;
        }

        int count = 0;
        for (Testresult test : tests) {
            if (test.isPassed()) {
                count++;
            }
        }

        final double percentage = (count * 1.0 / tests.size()) * 100;
        this.setPercentageOfTestsPassing((int) percentage);
    }
}
