package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Tag;
import fi.helsinki.cs.codebrowser.model.User;

import java.io.IOException;
import java.util.Collection;

public interface TagService {

    Collection<Tag> findAllBy(User user, String instanceId, String studentId, String courseId, String exerciseId);
    Tag create(User user, String instanceId, String studentId, String courseId, String exerciseId, Tag tag) throws IOException;
    Tag delete(User user, String instanceId, String studentId, String courseId, String exerciseId, Long tagId);

}
