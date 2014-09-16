package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Tag;

import java.io.IOException;
import java.util.Collection;

public interface TagService {

    Collection<Tag> findAllBy(String instanceId, String studentId, String courseId, String exerciseId);
    Tag create(String instanceId, String studentId, String courseId, String exerciseId, Tag tag) throws IOException;
    Tag delete(String instanceId, String studentId, String courseId, String exerciseId, Long tagId);

}
