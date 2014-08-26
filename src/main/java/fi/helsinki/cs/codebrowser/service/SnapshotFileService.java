package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.SnapshotFile;

import java.io.IOException;
import java.util.Collection;

public interface SnapshotFileService {

    Collection<SnapshotFile> findAllBy(String studentId, String courseId, String exerciseId, String snapshotId) throws IOException;
    SnapshotFile findBy(String studentId, String courseId, String exerciseId, String snapshotId, String fileId) throws IOException;
    String findContentBy(String studentId, String courseId, String exerciseId, String snapshotId, String fileId);

}
