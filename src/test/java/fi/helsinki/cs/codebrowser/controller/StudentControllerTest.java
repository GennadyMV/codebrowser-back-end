package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.exception.NotFoundException;
import fi.helsinki.cs.codebrowser.model.Student;
import fi.helsinki.cs.codebrowser.service.StudentService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public final class StudentControllerTest {

    private static final String INSTANCE = "TestInstanceId";
    private static final String COURSE = "TestCourseId";
    private static final String EXERCISE = "TestExerciseId";

    private static final String URL_INSTANCE = "/" + INSTANCE + "/students/";
    private static final String URL_INSTANCE_COURSE = "/" + INSTANCE + "/courses/" + COURSE + "/students/";
    private static final String URL_INSTANCE_COURSE_EXERCISE = "/" + INSTANCE + "/courses/" + COURSE + "/exercises/" + EXERCISE + "/students/";

    private static final String STUDENT_1 = "student1";
    private static final String STUDENT_2 = "student2";
    private static final String STUDENT_3 = "student3";

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private List<Student> students;
    private Student s1;
    private Student s2;
    private Student s3;

    private MockMvc mockMvc;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        buildStudents();
    }

    private void buildStudents() {

        students = new ArrayList<>();

        s1 = new Student();
        s1.setUsername(STUDENT_1);
        students.add(s1);

        s2 = new Student();
        s2.setUsername(STUDENT_2);
        students.add(s2);

        s3 = new Student();
        s3.setUsername(STUDENT_3);
        students.add(s3);
    }

    private void assertSameStudents(final Collection<Student> first, final Collection<Student> second) {

        assertEquals(first.size(), second.size());

        for (Student student1 : first) {
            boolean found = false;
            for (Student student2 : second) {
                if (student1.getUsername().equals(student2.getUsername())) {
                    found = true;
                }
            }
            assertTrue(found);
        }
    }

    @Test
    public void listForInstanceReturnsCorrectStudents() throws Exception {

        when(studentService.findAll(INSTANCE)).thenReturn(students);

        mockMvc.perform(get(URL_INSTANCE))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(3)))
               .andExpect(jsonPath("$[0].username", is(STUDENT_1)))
               .andExpect(jsonPath("$[1].username", is(STUDENT_2)))
               .andExpect(jsonPath("$[2].username", is(STUDENT_3)));

        verify(studentService).findAll(INSTANCE);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void listForInstanceAndCourseReturnsCorrectStudents() throws Exception {

        when(studentService.findAllBy(INSTANCE, COURSE)).thenReturn(students);

        mockMvc.perform(get(URL_INSTANCE_COURSE))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(3)))
               .andExpect(jsonPath("$[0].username", is(STUDENT_1)))
               .andExpect(jsonPath("$[1].username", is(STUDENT_2)))
               .andExpect(jsonPath("$[2].username", is(STUDENT_3)));

        verify(studentService).findAllBy(INSTANCE, COURSE);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void listForInstanceCourseAndExerciseReturnsCorrectStudents() throws Exception {

        when(studentService.findAllBy(INSTANCE, COURSE, EXERCISE)).thenReturn(students);

        mockMvc.perform(get(URL_INSTANCE_COURSE_EXERCISE))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(3)))
               .andExpect(jsonPath("$[0].username", is(STUDENT_1)))
               .andExpect(jsonPath("$[1].username", is(STUDENT_2)))
               .andExpect(jsonPath("$[2].username", is(STUDENT_3)));

        verify(studentService).findAllBy(INSTANCE, COURSE, EXERCISE);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void readForInstanceReturnsCorrectStudent() throws Exception {

        when(studentService.findByInstance(INSTANCE, STUDENT_1)).thenReturn(s1);

        mockMvc.perform(get(URL_INSTANCE + STUDENT_1))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.username", is(STUDENT_1)));

        verify(studentService).findByInstance(INSTANCE, STUDENT_1);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void readForInstanceCourseAndExerciseReturnsCorrectStudent() throws Exception {

        when(studentService.find(INSTANCE, COURSE, EXERCISE, STUDENT_1)).thenReturn(s1);

        mockMvc.perform(get(URL_INSTANCE_COURSE_EXERCISE + STUDENT_1))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.username", is(STUDENT_1)));

        verify(studentService).find(INSTANCE, COURSE, EXERCISE, STUDENT_1);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void listForInstanceHandlesNotFound() throws Exception {

        when(studentService.findAll(INSTANCE)).thenThrow(new NotFoundException());

        mockMvc.perform(get(URL_INSTANCE))
                .andExpect(status().isNotFound());

        verify(studentService).findAll(INSTANCE);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void listForInstanceAndCourseHandlesNotFound() throws Exception {

        when(studentService.findAllBy(INSTANCE, COURSE)).thenThrow(new NotFoundException());

        mockMvc.perform(get(URL_INSTANCE_COURSE))
                .andExpect(status().isNotFound());

        verify(studentService).findAllBy(INSTANCE, COURSE);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void listForInstanceCourseAndexerciseHandlesNotFound() throws Exception {

        when(studentService.findAllBy(INSTANCE, COURSE, EXERCISE)).thenThrow(new NotFoundException());

        mockMvc.perform(get(URL_INSTANCE_COURSE_EXERCISE))
                .andExpect(status().isNotFound());

        verify(studentService).findAllBy(INSTANCE, COURSE, EXERCISE);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void readForInstanceHandlesNotFound() throws Exception {

        when(studentService.findByInstance(INSTANCE, STUDENT_1)).thenThrow(new NotFoundException());

        mockMvc.perform(get(URL_INSTANCE + STUDENT_1))
                .andExpect(status().isNotFound());

        verify(studentService).findByInstance(INSTANCE, STUDENT_1);
        verifyNoMoreInteractions(studentService);
    }

    @Test
    public void readForInstanceCourseAndexerciseHandlesNotFound() throws Exception {

        when(studentService.find(INSTANCE, COURSE, EXERCISE, STUDENT_1)).thenThrow(new NotFoundException());

        mockMvc.perform(get(URL_INSTANCE_COURSE_EXERCISE + STUDENT_1))
                .andExpect(status().isNotFound());

        verify(studentService).find(INSTANCE, COURSE, EXERCISE, STUDENT_1);
        verifyNoMoreInteractions(studentService);
    }
}
