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

    public String getId() {
        return id;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getUserId() {
        return userId;
    }
}
