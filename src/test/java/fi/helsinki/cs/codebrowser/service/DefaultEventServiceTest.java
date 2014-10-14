package fi.helsinki.cs.codebrowser.service;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.model.Event;
import fi.helsinki.cs.codebrowser.testutil.BackendServerStub;

import java.io.IOException;
import java.util.Date;
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

import static fi.helsinki.cs.codebrowser.testutil.BackendServerStub.*;


import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@ActiveProfiles("test")
public final class DefaultEventServiceTest {

    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(8089);

    private static final BackendServerStub SERVER = new BackendServerStub();

    @Autowired
    private EventService eventService;

    @BeforeClass
    public static void setUpClass() {

        SERVER.initialiseServer();
    }

    @Test
    public void findAllRetrievesJsonAndParsesItToEvents() throws IOException {

        final List<Event> events = eventService.findAll(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID);

        assertEquals(3, events.size());
        assertEquals(EVENT_ID, events.get(2).getId());
    }

    @Test
    public void findRetrievesJsonAndParsesItToCourse() throws IOException {

        final Event event = eventService.find(INSTANCE_ID, STUDENT_ID, COURSE_ID, EXERCISE_ID, EVENT_ID);

        assertEquals(EVENT_ID, event.getId());
        assertEquals(new Date(Long.parseLong(EVENT_TIMESTAMP)), event.getTimestamp());
        assertEquals(EVENT_TYPE, event.getEventType());
    }
}
