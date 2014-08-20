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
public class CourseControllerTest {

    private static final String COURSE = "courseId";
    private static final String USER = "mockUserId";
    private static final String COURSE_1_NAME = "course1Name";
    private static final String COURSE_2_NAME = "course2Name";

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

        final Course c1 = new Course();
        c1.setName(COURSE_1_NAME);
        courses.add(c1);

        final Course c2 = new Course();
        c2.setName(COURSE_2_NAME);
        courses.add(c2);

        return courses;
    }

    @Test
    public void listShouldReturnAllCourses() throws Exception {

        final List<Course> courses = buildCourseList();

        when(courseService.findAll()).thenReturn(courses);

        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(COURSE_1_NAME)))
                .andExpect(jsonPath("$[1].name", is(COURSE_2_NAME)));

        verify(courseService, times(1)).findAll();
        verifyNoMoreInteractions(courseService);
    }

    @Test
    public void listByStudentShouldReturnAllCourses() throws Exception {

        final List<Course> courses = buildCourseList();

        when(courseService.findAllBy(USER)).thenReturn(courses);

        mockMvc.perform(get("/students/" + USER + "/courses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(COURSE_1_NAME)))
                .andExpect(jsonPath("$[1].name", is(COURSE_2_NAME)));

        verify(courseService, times(1)).findAllBy(USER);
        verifyNoMoreInteractions(courseService);
    }

    @Test
    public void readShouldReturnCourse() throws Exception {

        final Course course = new Course();
        course.setName(COURSE_1_NAME);

        when(courseService.findBy(COURSE)).thenReturn(course);

        mockMvc.perform(get("/courses/" + COURSE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(COURSE_1_NAME)));

        verify(courseService, times(1)).findBy(COURSE);
        verifyNoMoreInteractions(courseService);
    }

    @Test
    public void readByStudentShouldReturnCourse() throws Exception {

        final Course course = new Course();
        course.setName(COURSE_1_NAME);

        when(courseService.findBy(USER, COURSE)).thenReturn(course);

        mockMvc.perform(get("/students/" + USER + "/courses/" + COURSE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(COURSE_1_NAME)));

        verify(courseService, times(1)).findBy(USER, COURSE);
        verifyNoMoreInteractions(courseService);
    }
}
