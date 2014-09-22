package fi.helsinki.cs.codebrowser.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.SnapshotFile;
import fi.helsinki.cs.codebrowser.testutil.BackendServerStub;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static fi.helsinki.cs.codebrowser.testutil.BackendServerStub.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@ActiveProfiles("test")
public final class DefaultSnapshotFileServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    private final BackendServerStub testUtil = new BackendServerStub();

    @Autowired
    private SnapshotFileService snapshotFileService;

    private void assertEitherHasName(final List<SnapshotFile> files, final String name) {

        for (SnapshotFile file : files) {
            if (file.getName().equals(name)) {
                return;
            }
        }

        fail("No element has name " + name);
    }

    @Before
    public void setUp() {

        testUtil.reset();
    }

    @Test
    public void findAllByReturnsCorrectSnapshotFiles() throws IOException {

        final List<SnapshotFile> files = (List<SnapshotFile>) snapshotFileService.findAllBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, SNAPSHOT_ID);

        assertEquals(2, files.size());
        assertEitherHasName(files, FILE_NAME);
    }

    @Test(expected = NotFoundException.class)
    public void findAllByThrowsNotFoundWhenNoMatchingItemIsFound() throws IOException {

        snapshotFileService.findAllBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, NO_SUCH_ID);
    }

    @Test
    public void findByReturnsCorrectSnapshotFile() throws IOException {

        final SnapshotFile file = snapshotFileService.findBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, SNAPSHOT_ID, FILE_ID);

        assertEquals(FILE_NAME, file.getName());
    }

    @Test(expected = NotFoundException.class)
    public void findByThrowsNotFoundWhenNoMatchingElementIsFound() throws IOException {

        snapshotFileService.findBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, SNAPSHOT_ID, NO_SUCH_ID);
    }

    @Test
    public void findContentByReturnsCorrectSnapshotFile() throws IOException {

        final String content = snapshotFileService.findContentBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, SNAPSHOT_ID, FILE_ID);

        assertEquals(FILE_CONTENT, content);
    }

    @Test(expected = NotFoundException.class)
    public void findContentByThrowsNotFoundWhenNoMatchingElementIsFound() throws IOException {

        snapshotFileService.findContentBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, SNAPSHOT_ID, NO_SUCH_ID);
    }
}
