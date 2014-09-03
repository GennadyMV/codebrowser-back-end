package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Snapshot;

import java.io.IOException;
import java.util.Collection;

public interface SnapshotService {

    Collection<Snapshot> findAllBy(String instance, String studentId, String courseId, String exerciseId, String level) throws IOException;
    Snapshot findBy(String instance, String studentId, String courseId, String exerciseId, String snapshotId, String level) throws IOException;
    byte[] findAllFilesAsZip(String instance, String studentId, String courseId, String exerciseId, String level) throws IOException;

}
