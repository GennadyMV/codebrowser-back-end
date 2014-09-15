package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Tag;
import fi.helsinki.cs.codebrowser.repository.TagRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultTagService implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public void create(final Tag tag) {

        tagRepository.save(tag);
    }

    @Override
    public Collection<Tag> findAllBy(final String instanceId, final String studentId, final String courseId, final String exerciseId) {

        return tagRepository.findAllByInstanceIdAndStudentIdAndCourseIdAndExerciseId(instanceId, studentId, courseId, exerciseId);
    }
}
