package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Course {

    private String id;
    private String name;

    @JsonIgnore
    private List<Student> students;

    private List<Exercise> exercises;

    private int amountOfStudents;

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
        this.setAmountOfStudents(students.size());
    }

    public int getAmountOfStudents() {

        if (students != null && !students.isEmpty()) {
            amountOfStudents = students.size();
        } else {
            amountOfStudents = 0;
        }

        return amountOfStudents;
    }

    public void setAmountOfStudents(final int amountOfStudents) {

        this.amountOfStudents = amountOfStudents;
    }

    public String getName() {

        return name;
    }

    public String getId() {

        return id;
    }
}
