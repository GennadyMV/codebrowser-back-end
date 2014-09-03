package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.model.Course;
import fi.helsinki.cs.codebrowser.service.CourseService;

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
public final class CourseControllerTest {

    private static final String INSTANCE = "instance";
    private static final String COURSE = "courseID";
    private static final String USER = "userID";

    private static final String COURSE_A_NAME = "Course A";
    private static final String COURSE_B_NAME = "Course B";

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    private List<Course> buildCourseList() {

        final List<Course> courses = new ArrayList<>();

        final Course courseA = new Course();
        courseA.setName(COURSE_A_NAME);
        courses.add(courseA);

        final Course courseB = new Course();
        courseB.setName(COURSE_B_NAME);
        courses.add(courseB);

        return courses;
    }

    @Test
    public void listShouldReturnAllCourses() throws Exception {

        final List<Course> courses = buildCourseList();

        when(courseService.findAll(INSTANCE)).thenReturn(courses);

        mockMvc.perform(get("/" + INSTANCE + "/courses"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].name", is(COURSE_A_NAME)))
               .andExpect(jsonPath("$[1].name", is(COURSE_B_NAME)));

        verify(courseService, times(1)).findAll(INSTANCE);
        verifyNoMoreInteractions(courseService);
    }

    @Test
    public void listByStudentShouldReturnAllCourses() throws Exception {

        final List<Course> courses = buildCourseList();

        when(courseService.findAllBy(INSTANCE, USER)).thenReturn(courses);

        mockMvc.perform(get("/" + INSTANCE + "/students/" + USER + "/courses"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].name", is(COURSE_A_NAME)))
               .andExpect(jsonPath("$[1].name", is(COURSE_B_NAME)));

        verify(courseService, times(1)).findAllBy(INSTANCE, USER);
        verifyNoMoreInteractions(courseService);
    }

    @Test
    public void readShouldReturnCourse() throws Exception {

        final Course course = new Course();
        course.setName(COURSE_A_NAME);

        when(courseService.findBy(INSTANCE, COURSE)).thenReturn(course);

        mockMvc.perform(get("/" + INSTANCE + "/courses/" + COURSE))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.name", is(COURSE_A_NAME)));

        verify(courseService, times(1)).findBy(INSTANCE, COURSE);
        verifyNoMoreInteractions(courseService);
    }

    @Test
    public void readByStudentShouldReturnCourse() throws Exception {

        final Course course = new Course();
        course.setName(COURSE_A_NAME);

        when(courseService.findBy(INSTANCE, USER, COURSE)).thenReturn(course);

        mockMvc.perform(get("/" + INSTANCE + "/students/" + USER + "/courses/" + COURSE))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.name", is(COURSE_A_NAME)));

        verify(courseService, times(1)).findBy(INSTANCE, USER, COURSE);
        verifyNoMoreInteractions(courseService);
    }
}
