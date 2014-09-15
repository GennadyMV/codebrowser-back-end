package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Tag;

import java.util.Collection;

public interface TagService {

    Collection<Tag> findAllBy(String instanceId, String studentId, String courseId, String exerciseId);
    void create(Tag tag);

}
