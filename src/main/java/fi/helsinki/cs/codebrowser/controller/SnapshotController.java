package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.model.Snapshot;
import fi.helsinki.cs.codebrowser.service.SnapshotService;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "students/{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots")
public class SnapshotController {

    @Autowired
    private SnapshotService snapshotService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Snapshot> list(@PathVariable final String studentId,
                         @PathVariable final String courseId,
                         @PathVariable final String exerciseId) {

        return snapshotService.findAll(studentId, courseId, exerciseId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{snapshotId}")
    public Snapshot read(@PathVariable final String studentId,
                         @PathVariable final String courseId,
                         @PathVariable final String exerciseId,
                         @PathVariable final String snapshotId) {

        return snapshotService.find(studentId, courseId, exerciseId, snapshotId);
    }
}
