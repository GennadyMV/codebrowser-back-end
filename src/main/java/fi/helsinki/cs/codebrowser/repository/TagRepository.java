package fi.helsinki.cs.codebrowser.repository;

import fi.helsinki.cs.codebrowser.model.Tag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByUserIdAndInstanceIdAndStudentIdAndCourseIdAndExerciseIdAndId(Long userId, String instanceId, String studentId, String courseId, String exerciseId, Long id);
    List<Tag> findAllByUserIdAndInstanceIdAndStudentIdAndCourseIdAndExerciseId(Long userId, String instanceId, String studentId, String courseId, String exerciseId);

}
