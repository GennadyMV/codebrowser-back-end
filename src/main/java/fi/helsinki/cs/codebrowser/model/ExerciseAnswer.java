package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class ExerciseAnswer extends AbstractPersistable<Long> implements Serializable {

    @JsonIgnore
    @ManyToOne
    private Student student;

    @JsonIgnore
    @ManyToOne
    private Exercise exercise;

    @OneToMany(mappedBy = "exerciseAnswer")
    private List<Snapshot> snapshots;

    public Student getStudent() {

        return student;
    }

    public void setStudent(final Student student) {

        this.student = student;
    }

    public Exercise getExercise() {

        return exercise;
    }

    public void setExercise(final Exercise exercise) {

        this.exercise = exercise;
    }

    public List<Snapshot> getSnapshots() {

        if (snapshots != null) {
            Collections.sort(snapshots, new Comparator<Snapshot>() {
                @Override
                public int compare(final Snapshot o1, final Snapshot o2) {
                    return o1.getSnapshotTime().compareTo(o2.getSnapshotTime());
                }
            });

            for (Snapshot snapshot : snapshots) {
                Collections.sort(snapshot.getFiles(), new Comparator<SnapshotFile>() {
                    @Override
                    public int compare(final SnapshotFile o1, final SnapshotFile o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
            }
        }

        return snapshots;
    }

    public void setSnapshots(final List<Snapshot> snapshots) {

        this.snapshots = snapshots;
    }

    public void addSnapshot(final Snapshot snapshot) {

        if (getSnapshots().contains(snapshot)) {
            return;
        }

        getSnapshots().add(snapshot);
    }
}
