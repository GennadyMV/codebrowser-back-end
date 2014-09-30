package fi.helsinki.cs.codebrowser.controller;


import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.Tag;
import fi.helsinki.cs.codebrowser.model.User;
import fi.helsinki.cs.codebrowser.service.AuthorizationService;
import fi.helsinki.cs.codebrowser.service.TagService;

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

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;

import static org.hamcrest.CoreMatchers.is;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@ActiveProfiles("test")
public final class TagControllerTest {

    private static final String INSTANCE = "hy";
    private static final String COURSE = "ohpe";
    private static final String EXERCISE = "ex1";
    private static final String STUDENT = "01111";

    private static final String USERNAME = "userName";
    private static final Long USER = 2L;

    private static final Long TAG = 1L;
    private static final String TAG_NAME = "tag1";

    private static final String URL_A = "/hy/students/01111/courses/ohpe/exercises/ex1/tags";
    private static final String URL_B = "/hy/courses/ohpe/exercises/ex1/students/01111/tags";
    private static final String URL_C = "/hy/students/01111/courses/ohpe/exercises/ex1/tags/1";

    @Mock
    private TagService tagService;

    @Mock
    private AuthorizationService authorizationService;

    @InjectMocks
    private TagController tagController;

    private MockMvc mockMvc;

    private User user;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();

        user = mock(User.class);
        when(user.getId()).thenReturn(1L);
        when(user.getUsername()).thenReturn(USERNAME);
    }

    private Tag createTag(final String name) {

        final Tag tag = new Tag();

        tag.setName(name);
        tag.setCourseId("c");
        tag.setExerciseId("e");
        tag.setInstanceId("i");
        tag.setStudentId("s");

        return tag;
    }

    @Test
    public void listReturnsCorrectTagsForUrlA() throws Exception {

        final List<Tag> tags = new ArrayList<>();
        tags.add(createTag("tag1"));
        tags.add(createTag("tag2"));


        when(authorizationService.currentUser()).thenReturn(user);
        when(tagService.findAllBy(user, INSTANCE, STUDENT, COURSE, EXERCISE)).thenReturn(tags);

        mockMvc.perform(get(URL_A))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].name", is(TAG_NAME)))
               .andExpect(jsonPath("$[1].name", is("tag2")));

        verify(authorizationService).currentUser();
        verify(tagService).findAllBy(user, INSTANCE, STUDENT, COURSE, EXERCISE);
        verifyNoMoreInteractions(tagService, authorizationService);
    }

    @Test
    public void listReturnsCorrectTagsForUrlB() throws Exception {

        final List<Tag> tags = new ArrayList<>();
        tags.add(createTag("tag1"));
        tags.add(createTag("tag2"));

        when(authorizationService.currentUser()).thenReturn(user);
        when(tagService.findAllBy(user, INSTANCE, STUDENT, COURSE, EXERCISE)).thenReturn(tags);

        mockMvc.perform(get(URL_B))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].name", is(TAG_NAME)))
               .andExpect(jsonPath("$[1].name", is("tag2")));

        verify(authorizationService).currentUser();
        verify(tagService).findAllBy(user, INSTANCE, STUDENT, COURSE, EXERCISE);
        verifyNoMoreInteractions(tagService);
    }

    @Test
    public void listHandlesNotFoundException() throws Exception {

        when(authorizationService.currentUser()).thenReturn(user);
        when(tagService.findAllBy(user, INSTANCE, STUDENT, COURSE, EXERCISE)).thenThrow(new NotFoundException());

        mockMvc.perform(get(URL_A))
               .andExpect(status().isNotFound());

        verify(authorizationService).currentUser();
        verify(tagService).findAllBy(user, INSTANCE, STUDENT, COURSE, EXERCISE);
        verifyNoMoreInteractions(tagService, authorizationService);
    }

    @Test
    public void addTagCreatesNewTagAndReturnsItForUrlA() throws Exception {

        final Tag tag = createTag("tag3");

        when(authorizationService.currentUser()).thenReturn(user);
        when(tagService.create(eq(user), eq(INSTANCE), eq(STUDENT), eq(COURSE), eq(EXERCISE), any(Tag.class))).thenReturn(tag);

        mockMvc.perform(post(URL_A).contentType(MediaType.APPLICATION_JSON)
                                   .content("{\"name\": \"tag3\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name", is("tag3")));

        verify(authorizationService).currentUser();
        verify(tagService).create(eq(user), eq(INSTANCE), eq(STUDENT), eq(COURSE), eq(EXERCISE), any(Tag.class));
        verifyNoMoreInteractions(tagService, authorizationService);
    }

    @Test
    public void addTagCreatesNewTagAndReturnsItForUrlB() throws Exception {

        final Tag tag = createTag("tag3");

        when(authorizationService.currentUser()).thenReturn(user);
        when(tagService.create(eq(user), eq(INSTANCE), eq(STUDENT), eq(COURSE), eq(EXERCISE), any(Tag.class))).thenReturn(tag);

        mockMvc.perform(post(URL_B).contentType(MediaType.APPLICATION_JSON)
                                   .content("{\"name\": \"tag3\"}"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name", is("tag3")));

        verify(authorizationService).currentUser();
        verify(tagService).create(eq(user), eq(INSTANCE), eq(STUDENT), eq(COURSE), eq(EXERCISE), any(Tag.class));
        verifyNoMoreInteractions(tagService, authorizationService);
    }

    @Test
    public void invalidRequestReturnsBadRequest() throws Exception {

        mockMvc.perform(post(URL_A).contentType(MediaType.APPLICATION_JSON)
                                   .content("{\"foo\":\"bar\"}"))
               .andExpect(status().isBadRequest());

        verifyNoMoreInteractions(tagService);
    }

    @Test
    public void emptyNameReturnsBadRequest() throws Exception {

        mockMvc.perform(post(URL_A).contentType(MediaType.APPLICATION_JSON)
                                   .content("{\"name\":\"\"}"))
               .andExpect(status().isBadRequest());

        verifyNoMoreInteractions(tagService);
    }

    @Test
    public void deleteTagDeletesExistingTagAndReturnsIt() throws Exception {

        final List<Tag> tags = new ArrayList<>();
        tags.add(createTag("tag1"));
        tags.add(createTag("tag2"));

        when(authorizationService.currentUser()).thenReturn(user);
        when(tagService.delete(user, INSTANCE, STUDENT, COURSE, EXERCISE, TAG)).thenReturn(tags.get(0));

        mockMvc.perform(delete(URL_C))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.name", is(TAG_NAME)));

        verify(authorizationService).currentUser();
        verify(tagService).delete(user, INSTANCE, STUDENT, COURSE, EXERCISE, TAG);
        verifyNoMoreInteractions(tagService, authorizationService);
    }
}
