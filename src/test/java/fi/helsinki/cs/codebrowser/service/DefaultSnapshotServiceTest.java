package fi.helsinki.cs.codebrowser.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.Snapshot;
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

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static fi.helsinki.cs.codebrowser.testutil.BackendServerStub.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@ActiveProfiles("test")
public final class DefaultSnapshotServiceTest {

    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(8089);

    private static final String CODE = "code";
    private static final String KEY = "key";

    private final BackendServerStub server = new BackendServerStub();

    @Autowired
    private SnapshotService snapshotService;

    private void assertEitherHasId(final List<Snapshot> snapshots, final String id) {

        for (Snapshot snapshot : snapshots) {
            if (snapshot.getId().equals(id)) {
                return;
            }
        }

        fail("No element has id " + id);
    }

    @Before
    public void setUp() {

        server.reset();
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

        verify(getRequestedFor(urlMatching(STUDENT_COURSE_EXERCISE_SNAPSHOTS_URL + ".*")));
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

        verify(getRequestedFor(urlMatching(STUDENT_COURSE_EXERCISE_SNAPSHOT_URL + ".*")));
        verify(getRequestedFor(urlMatching(".*=code")));
        verify(getRequestedFor(urlMatching(".*=key")));
    }
}
