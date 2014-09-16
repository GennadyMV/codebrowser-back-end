package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.exception.BadRequestException;
import fi.helsinki.cs.codebrowser.model.Tag;
import fi.helsinki.cs.codebrowser.service.TagService;
import java.io.IOException;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "{instanceId}/students/{studentId}/courses/{courseId}/exercises/{exerciseId}/tags",
                          "{instanceId}/courses/{courseId}/exercises/{exerciseId}/students/{studentId}/tags" },
                produces = "application/json")
public final class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Tag> list(@PathVariable final String instanceId,
                                @PathVariable final String studentId,
                                @PathVariable final String courseId,
                                @PathVariable final String exerciseId) throws IOException {

        return tagService.findAllBy(instanceId, studentId, courseId, exerciseId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Tag create(@Valid @RequestBody final Tag tag,
                      final BindingResult bindingResult,
                      @PathVariable final String instanceId,
                      @PathVariable final String studentId,
                      @PathVariable final String courseId,
                      @PathVariable final String exerciseId) throws IOException {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }

        return tagService.create(instanceId, studentId, courseId, exerciseId, tag);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{tagId}")
    public Tag delete(@PathVariable final String instanceId,
                      @PathVariable final String studentId,
                      @PathVariable final String courseId,
                      @PathVariable final String exerciseId,
                      @PathVariable final Long tagId) throws IOException {

        return tagService.delete(instanceId, studentId, courseId, exerciseId, tagId);
    }
}
