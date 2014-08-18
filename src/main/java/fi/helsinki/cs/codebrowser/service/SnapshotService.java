package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Snapshot;

import java.util.Collection;

public interface SnapshotService {

    Collection<Snapshot> findAllBy(String studentId, String courseId, String exerciseId);

    Snapshot findBy(String studentId, String courseId, String exerciseId, String snapshotId);

}
