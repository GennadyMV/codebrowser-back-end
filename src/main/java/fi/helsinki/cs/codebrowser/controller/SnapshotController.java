package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.model.Snapshot;
import fi.helsinki.cs.codebrowser.service.SnapshotService;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(method = RequestMethod.GET,
                value = { "{instance}/students/{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots",
                          "{instance}/courses/{courseId}/exercises/{exerciseId}/students/{studentId}/snapshots" },
                produces = "application/json")
public final class SnapshotController {

    @Autowired
    private SnapshotService snapshotService;

    @RequestMapping
    public Collection<Snapshot> list(@PathVariable final String instance,
                                     @PathVariable final String studentId,
                                     @PathVariable final String courseId,
                                     @PathVariable final String exerciseId,
                                     @RequestParam(value = "level", defaultValue = "KEY", required = false) final String level) throws IOException {

        return snapshotService.findAllBy(instance, studentId, courseId, exerciseId, level);
    }

    @RequestMapping(value = "{snapshotId}")
    public Snapshot read(@PathVariable final String instance,
                         @PathVariable final String studentId,
                         @PathVariable final String courseId,
                         @PathVariable final String exerciseId,
                         @PathVariable final String snapshotId,
                         @RequestParam(value = "level", defaultValue = "KEY", required = false) final String level) throws IOException {

        return snapshotService.findBy(instance, studentId, courseId, exerciseId, snapshotId, level);
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "files.zip",
                    produces = "application/zip")
    public HttpEntity<byte[]> readFiles(@PathVariable final String instance,
                                        @PathVariable final String studentId,
                                        @PathVariable final String courseId,
                                        @PathVariable final String exerciseId,
                                        @RequestParam(value = "level", defaultValue = "KEY", required = false) final String level) throws IOException {

        return new HttpEntity<>(snapshotService.findAllFilesAsZip(instance, studentId, courseId, exerciseId, level));
    }
}
