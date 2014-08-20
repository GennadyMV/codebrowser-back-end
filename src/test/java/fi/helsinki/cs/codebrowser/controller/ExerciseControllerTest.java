package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.model.Exercise;
import fi.helsinki.cs.codebrowser.service.ExerciseService;

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
public class ExerciseControllerTest {


    private static final String STUDENT = "testStudentId";
    private static final String COURSE = "testCourseId";
    private static final String EXERCISE = "testExerciseId";

    private static final String EX_1_NAME = "Exercise1Name";
    private static final String EX_2_NAME = "Exercise2Name";

    @Mock
    private ExerciseService exerciseService;

    @InjectMocks
    private ExerciseController exerciseController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(exerciseController).build();
    }

    private List<Exercise> buildExeciseList() {

        final List<Exercise> exercises = new ArrayList<>();

        final Exercise e1 = new Exercise();
        e1.setName(EX_1_NAME);
        exercises.add(e1);

        final Exercise e2 = new Exercise();
        e2.setName(EX_2_NAME);
        exercises.add(e2);

        return exercises;
    }

    @Test
    public void listShouldReturnExercises() throws Exception {

        final List<Exercise> exercises = buildExeciseList();

        when(exerciseService.findAllBy(COURSE)).thenReturn(exercises);

        mockMvc.perform(get("/courses/" + COURSE + "/exercises"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(EX_1_NAME)))
                .andExpect(jsonPath("$[1].name", is(EX_2_NAME)));

        verify(exerciseService, times(1)).findAllBy(COURSE);
        verifyNoMoreInteractions(exerciseService);
    }

    @Test
    public void listForStudentShouldReturnExercises() throws Exception {

        final List<Exercise> exercises = buildExeciseList();

        when(exerciseService.findAllBy(STUDENT, COURSE)).thenReturn(exercises);

        mockMvc.perform(get("/students/" + STUDENT + "/courses/" + COURSE + "/exercises"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(EX_1_NAME)))
                .andExpect(jsonPath("$[1].name", is(EX_2_NAME)));

        verify(exerciseService, times(1)).findAllBy(STUDENT, COURSE);
        verifyNoMoreInteractions(exerciseService);
    }

    @Test
    public void readByExerciseAndCourseShouldReturnExercise() throws Exception {

        final Exercise exercise = new Exercise();
        exercise.setName(EX_1_NAME);

        when(exerciseService.findBy(COURSE, EXERCISE)).thenReturn(exercise);

        mockMvc.perform(get("/courses/" + COURSE + "/exercises/" + EXERCISE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(EX_1_NAME)));

        verify(exerciseService, times(1)).findBy(COURSE, EXERCISE);
        verifyNoMoreInteractions(exerciseService);
    }

    @Test
    public void readByExerciseAndCourseAndUserShouldReturnExercise() throws Exception {

        final Exercise exercise = new Exercise();
        exercise.setName(EX_1_NAME);

        when(exerciseService.findBy(STUDENT, COURSE, EXERCISE)).thenReturn(exercise);

        mockMvc.perform(get("/students/" + STUDENT + "/courses/" + COURSE + "/exercises/" + EXERCISE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(EX_1_NAME)));

        verify(exerciseService, times(1)).findBy(STUDENT, COURSE, EXERCISE);
        verifyNoMoreInteractions(exerciseService);
    }
}
