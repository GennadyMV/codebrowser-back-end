package fi.helsinki.cs.codebrowser.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.Snapshot;
import fi.helsinki.cs.codebrowser.testutil.BackendServerStub;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static fi.helsinki.cs.codebrowser.testutil.BackendServerStub.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@ActiveProfiles("test")
public final class DefaultSnapshotServiceTest {

    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(8089);

    private static final BackendServerStub SERVER = new BackendServerStub();

    private static final String CODE = "code";
    private static final String KEY = "key";

    @Autowired
    private SnapshotService snapshotService;

    @BeforeClass
    public static void setUpClass() {

        SERVER.initialiseServer();
    }

    private void assertEitherHasId(final List<Snapshot> snapshots, final String id) {

        for (Snapshot snapshot : snapshots) {
            if (snapshot.getId().equals(id)) {
                return;
            }
        }

        fail("No element has id " + id);
    }

    @Test
    public void findAllReturnsCorrectSnapshots() throws IOException {

        final List<Snapshot> snapshots = (List<Snapshot>) snapshotService.findAllBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, KEY);

        assertEquals(1, snapshots.size());
        assertEitherHasId(snapshots, SNAPSHOT_ID);
    }

    @Test
    public void findAllPassesOnLevel() throws IOException {

        snapshotService.findAllBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, KEY);
        snapshotService.findAllBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, CODE);

        verify(getRequestedFor(urlMatching(STUDENT_COURSE_EXERCISE_SNAPSHOTS_URL + ANY)));
        verify(getRequestedFor(urlMatching(".*=code")));
        verify(getRequestedFor(urlMatching(".*=key")));
    }

    @Test(expected = NotFoundException.class)
    public void findAllThrowsNotFoundWhenNoMatchingElementsAreFound() throws IOException {

        snapshotService.findAllBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, NO_SUCH_ID, KEY);
    }

    @Test
    public void findByReturnsCorrectSnapshot() throws IOException {

        final Snapshot snapshot = snapshotService.findBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, SNAPSHOT_ID, CODE);

        assertEquals(SNAPSHOT_ID, snapshot.getId());
    }

    @Test
    public void findByPassesOnLevel() throws IOException {

        snapshotService.findBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, SNAPSHOT_ID, CODE);
        snapshotService.findBy(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, SNAPSHOT_ID, KEY);

        verify(getRequestedFor(urlMatching(STUDENT_COURSE_EXERCISE_SNAPSHOT_URL + ANY)));
        verify(getRequestedFor(urlMatching(".*=code")));
        verify(getRequestedFor(urlMatching(".*=key")));
    }

    @Test
    public void findAllFilesAsZipReturnsCorrectZip() throws IOException {

        final byte[] expected = {0x00, 0x01, 0x02, 0x03};

        final byte[] zip = snapshotService.findAllFilesAsZip(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, CODE);

        assertEquals(4, zip.length);
        assertTrue(Arrays.equals(zip, expected));
    }

    @Test(expected = NotFoundException.class)
    public void findAllFilesAsZipThrowsNotFoundForNonExistantSnapshot() throws IOException {

        snapshotService.findAllFilesAsZip(INSTANCE_ID, STUDENT_ID, COURSE_ID, NO_SUCH_ID, CODE);
    }

    @Test
    public void findAllFilesAsZipPassesOnLevel() throws IOException {

        snapshotService.findAllFilesAsZip(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, CODE);
        snapshotService.findAllFilesAsZip(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, KEY);

        verify(getRequestedFor(urlMatching(STUDENT_COURSE_EXERCISE_SNAPSHOTS_ZIP_URL + ANY)));
        verify(getRequestedFor(urlMatching(".*=code")));
        verify(getRequestedFor(urlMatching(".*=key")));
    }
}
