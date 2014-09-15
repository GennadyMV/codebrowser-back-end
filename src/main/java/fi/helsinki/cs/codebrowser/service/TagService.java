package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Tag;

import java.io.IOException;
import java.util.Collection;

public interface TagService {

    Collection<Tag> findAllBy(final String instanceId, final String studentId, final String courseId, final String exerciseId);
    Tag create(final String instanceId, final String studentId, final String courseId, final String exerciseId, final Tag tag) throws IOException;

}
