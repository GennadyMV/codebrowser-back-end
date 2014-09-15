package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Tag;
import java.util.Collection;

public interface TagService {

    void create(final Tag tag);
    Collection<Tag> findAllBy(final String instanceId, final String studentId, final String courseId, final String exerciseId);

}
