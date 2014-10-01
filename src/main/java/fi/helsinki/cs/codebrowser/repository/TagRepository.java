package fi.helsinki.cs.codebrowser.repository;

import fi.helsinki.cs.codebrowser.model.Tag;
import fi.helsinki.cs.codebrowser.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findByUserAndInstanceIdAndStudentIdAndCourseIdAndExerciseIdAndId(User user, String instanceId, String studentId, String courseId, String exerciseId, Long id);
    List<Tag> findAllByUserAndInstanceIdAndStudentIdAndCourseIdAndExerciseId(User user, String instanceId, String studentId, String courseId, String exerciseId);

}
