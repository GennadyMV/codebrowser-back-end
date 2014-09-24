package fi.helsinki.cs.codebrowser.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.exception.BadRequestException;
import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.Tag;
import fi.helsinki.cs.codebrowser.repository.TagRepository;
import fi.helsinki.cs.codebrowser.testutil.BackendServerStub;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static fi.helsinki.cs.codebrowser.testutil.BackendServerStub.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@ActiveProfiles("test")
@WebAppConfiguration
public final class DefaultTagServiceTest {

    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(8089);

    private static final String TAG_NAME = "MyTagName";

    private final BackendServerStub server = new BackendServerStub();

    @Autowired
    private TagService tagService;

    @Autowired
    private TagRepository tagRepository;

    @Before
    public void setUp() {

        tagRepository.deleteAll();
        server.reset();
    }

    private Tag newTag(final String name) {

        final Tag tag = new Tag();
        tag.setName(name);

        return tag;
    }

    @Test(expected = NotFoundException.class)
    public void findAllByThrowsNotFoundIfNothingIsFound() throws IOException {

        tagService.findAllBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, NO_SUCH_ID);
    }

    @Test
    public void createCreatesNewTagAndFindAllRetrievesIt() throws IOException {

        final Tag tag = newTag(TAG_NAME);

        tagService.create(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, tag);

        final List<Tag> tags = (List<Tag>) tagService.findAllBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID);
        final Tag created = tags.get(0);

        assertEquals(1, tags.size());
        assertEquals(TAG_NAME, created.getName());
        assertEquals(STUDENT_ID, created.getStudentId());
        assertEquals(INSTANCE_ID, created.getInstanceId());
        assertEquals(COURSE_ID, created.getCourseId());
        assertEquals(EXERCISE_ID, created.getExerciseId());
    }

    @Test(expected = BadRequestException.class)
    public void createThrowsBadRequestExceptionOnInvalidParams() throws IOException {

        tagService.create(INSTANCE_ID, STUDENT_ID, COURSE_ID, NO_SUCH_ID, newTag(TAG_NAME));
    }

    @Test
    public void deleteDeletesTag() throws IOException {

        final Tag created = tagService.create(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, newTag(TAG_NAME));
        tagService.create(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, newTag("tag2"));

        tagService.delete(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, created.getId());

        final List<Tag> tags = (List<Tag>) tagService.findAllBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID);

        assertEquals(1, tags.size());
    }

    @Test(expected = NotFoundException.class)
    public void deleteThrowsNotFoundIfSpecifiedTagDoesNotExist() throws IOException {

        tagService.delete(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, 1L);
    }
}
