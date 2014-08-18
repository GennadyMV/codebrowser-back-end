package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.model.Snapshot;
import fi.helsinki.cs.codebrowser.service.SnapshotService;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(method = RequestMethod.GET,
                value = { "students/{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots",
                          "courses/{courseId}/exercises/{exerciseId}/students/{studentId}/snapshots" },
                produces = "application/json")
public class SnapshotController {

    @Autowired
    private SnapshotService snapshotService;

    @RequestMapping
    public Collection<Snapshot> list(@PathVariable final String studentId,
                                     @PathVariable final String courseId,
                                     @PathVariable final String exerciseId) throws IOException {

        return snapshotService.findAllBy(studentId, courseId, exerciseId);
    }

    @RequestMapping(value = "{snapshotId}")
    public Snapshot read(@PathVariable final String studentId,
                         @PathVariable final String courseId,
                         @PathVariable final String exerciseId,
                         @PathVariable final String snapshotId) throws IOException {

        return snapshotService.findBy(studentId, courseId, exerciseId, snapshotId);
    }
}
