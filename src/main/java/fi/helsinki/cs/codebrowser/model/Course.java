package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

public class Course {

    private String id;
    private String name;

    @JsonIgnore
    private List<Student> students;

    private List<Exercise> exercises;

    public void addExercise(final Exercise exercise) {

        if (getExercises().contains(exercise)) {
            return;
        }

        getExercises().add(exercise);
    }

    public List<Exercise> getExercises() {

        if (exercises == null) {
            exercises = new ArrayList<>();
        }

        return exercises;
    }

    public void setExercises(final List<Exercise> exercises) {

        this.exercises = exercises;
    }

    public List<Student> getStudents() {

        return students;
    }

    public void setStudents(final List<Student> students) {

        this.students = students;
    }

    public void setName(final String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setPlainId(final String id) {
        this.id = id;
    }

    public String getId() {

        return Base64.encodeBase64URLSafeString(name.getBytes());
    }

    @JsonIgnore
    public String getPlainId() {

        return id;
    }
}
