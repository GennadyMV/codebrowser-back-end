package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.model.Tag;
import fi.helsinki.cs.codebrowser.repository.TagRepository;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {
            "{instanceId}/students/{studentId}/courses/{courseId}/exercises/{exerciseId}/snapshots/{snapshotId}/tags",
            "{instanceId}/courses/{courseId}/exercises/{exerciseId}/students/{studentId}/snapshots/{snapshotId}/tags" },
        produces = "application/json")
public final class TagController {

    @Autowired
    private TagRepository tagRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Tag> list(@PathVariable final String snapshotId) throws IOException {

        return tagRepository.findAllBySnapshotId(snapshotId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addTag(@ModelAttribute final Tag tag) {

        tagRepository.save(tag);
    }

}
