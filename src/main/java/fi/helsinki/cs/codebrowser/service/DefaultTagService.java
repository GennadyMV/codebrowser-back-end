package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.exception.BadRequestException;
import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.Exercise;
import fi.helsinki.cs.codebrowser.model.Tag;
import fi.helsinki.cs.codebrowser.model.User;
import fi.helsinki.cs.codebrowser.repository.TagRepository;

import java.io.IOException;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class DefaultTagService implements TagService {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private TagRepository tagRepository;

    private Tag findBy(final User user,
                       final String instanceId,
                       final String studentId,
                       final String courseId,
                       final String exerciseId,
                       final Long tagId) {

        final Tag tag = tagRepository.findByUserIdAndInstanceIdAndStudentIdAndCourseIdAndExerciseIdAndId(user.getId(),
                                                                                                         instanceId,
                                                                                                         studentId,
                                                                                                         courseId,
                                                                                                         exerciseId,
                                                                                                         tagId);

        if (tag == null) {
            throw new NotFoundException();
        }

        return tag;
    }

    @Override
    public Collection<Tag> findAllBy(final User user,
                                     final String instanceId,
                                     final String studentId,
                                     final String courseId,
                                     final String exerciseId) {

        return tagRepository.findAllByUserIdAndInstanceIdAndStudentIdAndCourseIdAndExerciseId(user.getId(),
                                                                                              instanceId,
                                                                                              studentId,
                                                                                              courseId,
                                                                                              exerciseId);

    }

    @Override
    @Transactional
    public Tag create(final User user,
                      final String instanceId,
                      final String studentId,
                      final String courseId,
                      final String exerciseId,
                      final Tag tag) throws IOException {

        final Exercise exercise;

        try {
            exercise = exerciseService.findBy(instanceId, studentId, courseId, exerciseId);
        } catch (NotFoundException exception) {
            throw new BadRequestException();
        }

        tag.setInstanceId(instanceId);
        tag.setStudentId(studentId);
        tag.setCourseId(courseId);
        tag.setExerciseId(exerciseId);
        tag.setUser(user);

        return tagRepository.save(tag);
    }

    @Override
    @Transactional
    public Tag delete(final User user,
                      final String instanceId,
                      final String studentId,
                      final String courseId,
                      final String exerciseId,
                      final Long tagId) {

        final Tag tag = findBy(user, instanceId, studentId, courseId, exerciseId, tagId);
        tagRepository.delete(tag);

        return tag;
    }
}
