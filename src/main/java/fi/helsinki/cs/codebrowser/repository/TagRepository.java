package fi.helsinki.cs.codebrowser.repository;

import fi.helsinki.cs.codebrowser.model.Tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findAllByInstanceIdAndStudentIdAndCourseIdAndExerciseId(String instanceId, String studentId, String courseId, String exerciseId);

}
