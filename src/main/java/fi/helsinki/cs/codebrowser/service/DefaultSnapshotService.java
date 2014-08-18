package fi.helsinki.cs.codebrowser.service;

import fi.helsinki.cs.codebrowser.model.Snapshot;
import fi.helsinki.cs.codebrowser.model.Student;
import fi.helsinki.cs.codebrowser.web.client.SnapshotApiRestTemplate;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultSnapshotService implements SnapshotService {

    @Autowired
    private SnapshotApiRestTemplate restTemplate;

    @Autowired
    private StudentService studentService;

    @Override
    public Collection<Snapshot> findAllBy(final String studentId, final String courseId, final String exerciseId) throws IOException {

        final Student student = studentService.find(studentId);

        return restTemplate.getForObject("{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots",
                                         List.class, student.getId(), courseId, exerciseId);
    }

    @Override
    public Snapshot findBy(final String studentId, final String courseId, final String exerciseId, final String snapshotId) throws IOException {

        final Student student = studentService.find(studentId);

        return restTemplate.getForObject("{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/{snapshotId}",
                                         Snapshot.class, student.getId(), courseId, exerciseId, snapshotId);
    }
}
