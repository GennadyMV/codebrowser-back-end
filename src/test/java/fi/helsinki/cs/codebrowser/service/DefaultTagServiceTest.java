package fi.helsinki.cs.codebrowser.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.exception.BadRequestException;
import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.Tag;
import fi.helsinki.cs.codebrowser.model.User;
import fi.helsinki.cs.codebrowser.repository.TagRepository;
import fi.helsinki.cs.codebrowser.repository.UserRepository;
import fi.helsinki.cs.codebrowser.testutil.BackendServerStub;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
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
@WebAppConfiguration
@ActiveProfiles("test")
public final class DefaultTagServiceTest {

    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(8089);

    private static final BackendServerStub SERVER = new BackendServerStub();

    private static final String TAG_NAME = "MyTagName";

    private static final String USERNAME = "username";
    private static final String PASSWORD = "passwordpasswordpassword";

    @Autowired
    private TagService tagService;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeClass
    public static void setUpClass() {

        SERVER.initialiseServer();
    }

    @Before
    public void setUp() {

        tagRepository.deleteAll();
        userRepository.deleteAll();

        user = new User();

        user.setUsername(USERNAME);
        user.setPassword(PASSWORD);

        user = userRepository.save(user);
    }

    private Tag newTag(final String name) {

        final Tag tag = new Tag();
        tag.setName(name);

        return tag;
    }

    @Test
    public void findAllByReturnsEmptyListIfNothingIsFound() throws IOException {

        final Collection<Tag> tags = tagService.findAllBy(user, INSTANCE_ID, STUDENT_ID, COURSE_ID, NO_SUCH_ID);

        assertEquals(0, tags.size());
    }

    @Test
    public void createCreatesNewTagAndFindAllRetrievesIt() throws IOException {

        final Tag tag = newTag(TAG_NAME);

        tagService.create(user, INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, tag);

        final List<Tag> tags = (List<Tag>) tagService.findAllBy(user, INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID);
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

        tagService.create(user, INSTANCE_ID, STUDENT_ID, COURSE_ID, NO_SUCH_ID, newTag(TAG_NAME));
    }

    @Test
    public void deleteDeletesTag() throws IOException {

        final Tag created = tagService.create(user, INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, newTag(TAG_NAME));
        tagService.create(user, INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, newTag("tag2"));

        tagService.delete(user, INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, created.getId());

        final List<Tag> tags = (List<Tag>) tagService.findAllBy(user, INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID);

        assertEquals(1, tags.size());
    }

    @Test(expected = NotFoundException.class)
    public void deleteThrowsNotFoundIfSpecifiedTagDoesNotExist() throws IOException {

        tagService.delete(user, INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, 1L);
    }
}
