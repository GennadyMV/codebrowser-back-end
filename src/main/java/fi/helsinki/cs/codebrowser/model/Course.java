package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

public final class Course {

    public interface SimpleView { }
    public interface ExercisesView extends SimpleView { }

    private String id;
    private String name;
    private List<Exercise> exercises;

    public void setPlainId(final String id) {

        this.id = id;
    }

    @JsonIgnore
    public String getPlainId() {

        return id;
    }

    @JsonView(SimpleView.class)
    public String getId() {

        return Base64.encodeBase64URLSafeString(name.getBytes());
    }

    public void setName(final String name) {

        this.name = name;
    }

    @JsonView(SimpleView.class)
    public String getName() {

        return name;
    }

    public void setExercises(final List<Exercise> exercises) {

        this.exercises = exercises;
    }

    @JsonView(ExercisesView.class)
    public List<Exercise> getExercises() {

        if (exercises == null) {
            exercises = new ArrayList<>();
        }

        return exercises;
    }

    public void addExercise(final Exercise exercise) {

        if (getExercises().contains(exercise)) {
            return;
        }

        getExercises().add(exercise);
    }
}
