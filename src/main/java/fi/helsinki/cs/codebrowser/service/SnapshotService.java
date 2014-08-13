package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Snapshot;

import java.util.Collection;

public interface SnapshotService {

    Collection<Snapshot> findAll(String studentId, String courseId, String exerciseId);
    Snapshot find(String studentId, String courseId, String exerciseId, String snapshotId);

}
