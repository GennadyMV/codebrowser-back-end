package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class TmcSubmission {

    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("course_id")
    private String courseId;

    @JsonProperty("exercise_name")
    private String exerciseName;

    public void setId(final String id) {

        this.id = id;
    }

    public String getId() {

        return id;
    }

    public void setUserId(final String userId) {

        this.userId = userId;
    }

    public String getUserId() {

        return userId;
    }

    public void setCourseId(final String courseId) {

        this.courseId = courseId;
    }

    public String getCourseId() {

        return courseId;
    }

    public void setExerciseName(final String exerciseName) {

        this.exerciseName = exerciseName;
    }

    public String getExerciseName() {

        return exerciseName;
    }
}
