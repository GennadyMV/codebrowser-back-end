package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.model.Event;
import fi.helsinki.cs.codebrowser.service.EventService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@ActiveProfiles("test")
public final class EventControllerTest {

    private static final String EVENT_A_ID = "eventA";
    private static final String EVENT_B_ID = "eventB";

    private static final String INSTANCE = "instance";
    private static final String STUDENT = "studentID";
    private static final String COURSE = "courseID";
    private static final String EXERCISE = "exerciseID";
    private static final String EVENT = "eventId";

    private static final String BASE_URL_A = "/" + INSTANCE + "/students/" + STUDENT + "/courses/" + COURSE + "/exercises/" + EXERCISE + "/events/";
    private static final String BASE_URL_B = "/" + INSTANCE + "/courses/" + COURSE + "/exercises/" + EXERCISE + "/students/" + STUDENT + "/events/";

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    private List<Event> buildEventList() {

        final List<Event> events = new ArrayList<>();

        final Event eventA = new Event();
        eventA.setId(EVENT_A_ID);
        events.add(eventA);

        final Event eventB = new Event();
        eventB.setId(EVENT_B_ID);
        events.add(eventB);

        return events;
    }

    @Test
    public void listReturnsAllEventsForUrlStartingWithStudent() throws Exception {

        final List<Event> events = buildEventList();

        when(eventService.findAllBy(INSTANCE, STUDENT, COURSE, EXERCISE)).thenReturn(events);

        mockMvc.perform(get(BASE_URL_A))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].id", is(EVENT_A_ID)))
               .andExpect(jsonPath("$[1].id", is(EVENT_B_ID)));

        verify(eventService, times(1)).findAllBy(INSTANCE, STUDENT, COURSE, EXERCISE);
        verifyNoMoreInteractions(eventService);
    }

    @Test
    public void listReturnsAllEventsForUrlStartingWithCourse() throws Exception {

        final List<Event> events = buildEventList();

        when(eventService.findAllBy(INSTANCE, STUDENT, COURSE, EXERCISE)).thenReturn(events);

        mockMvc.perform(get(BASE_URL_B))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].id", is(EVENT_A_ID)))
               .andExpect(jsonPath("$[1].id", is(EVENT_B_ID)));

        verify(eventService, times(1)).findAllBy(INSTANCE, STUDENT, COURSE, EXERCISE);
        verifyNoMoreInteractions(eventService);
    }

    @Test
    public void readReturnsEventForUrlStartingWithStudent() throws Exception {

        final Event event = new Event();
        event.setId(EVENT);

        when(eventService.findBy(INSTANCE, STUDENT, COURSE, EXERCISE, EVENT)).thenReturn(event);

        mockMvc.perform(get(BASE_URL_A + "/" + EVENT))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.id", is(EVENT)));

        verify(eventService, times(1)).findBy(INSTANCE, STUDENT, COURSE, EXERCISE, EVENT);
        verifyNoMoreInteractions(eventService);
    }

    @Test
    public void readReturnsEventForUrlStartingWithCourse() throws Exception {

        final Event event = new Event();
        event.setId(EVENT);

        when(eventService.findBy(INSTANCE, STUDENT, COURSE, EXERCISE, EVENT)).thenReturn(event);

        mockMvc.perform(get(BASE_URL_B + "/" + EVENT))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.id", is(EVENT)));

        verify(eventService, times(1)).findBy(INSTANCE, STUDENT, COURSE, EXERCISE, EVENT);
        verifyNoMoreInteractions(eventService);
    }
}
