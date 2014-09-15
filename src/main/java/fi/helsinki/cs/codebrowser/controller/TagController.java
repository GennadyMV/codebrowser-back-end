package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.model.Tag;

import fi.helsinki.cs.codebrowser.service.TagService;
import java.io.IOException;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "{instanceId}/students/{studentId}/courses/{courseId}/exercises/{exerciseId}/tags",
                          "{instanceId}/courses/{courseId}/exercises/{exerciseId}/students/{studentId}/tags" },
                produces = "application/json")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Tag> list(@PathVariable final String instanceId,
                                @PathVariable final String studentId,
                                @PathVariable final String courseId,
                                @PathVariable final String exerciseId) throws IOException {

        return tagService.findAllBy(instanceId, studentId, courseId, exerciseId);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST)
    public void addTag(@ModelAttribute final Tag tag) {

        tagService.create(tag);
    }

}
