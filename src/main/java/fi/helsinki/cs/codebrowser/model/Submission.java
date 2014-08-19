package fi.helsinki.cs.codebrowser.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Submission {

    @JsonProperty("exercise_name")
    private String exerciseName;

    private String id;

    @JsonProperty("course_id")
    private String courseId;

    @JsonProperty("user_id")
    private String userId;

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(final String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(final String courseId) {
        this.courseId = courseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }
}
