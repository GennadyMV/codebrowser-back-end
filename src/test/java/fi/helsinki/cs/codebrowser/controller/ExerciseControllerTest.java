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
public final class ExerciseControllerTest {

    private static final String INSTANCE = "instance";
    private static final String STUDENT = "studentID";
    private static final String COURSE = "courseID";
    private static final String EXERCISE = "exerciseID";

    private static final String EXERCISE_A_NAME = "Exercise A";
    private static final String EXERCISE_B_NAME = "Exercise B";

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

        final Exercise exerciseA = new Exercise();
        exerciseA.setName(EXERCISE_A_NAME);
        exercises.add(exerciseA);

        final Exercise exerciseB = new Exercise();
        exerciseB.setName(EXERCISE_B_NAME);
        exercises.add(exerciseB);

        return exercises;
    }

    @Test
    public void listShouldReturnExercises() throws Exception {

        final List<Exercise> exercises = buildExeciseList();

        when(exerciseService.findAllBy(INSTANCE, COURSE)).thenReturn(exercises);

        mockMvc.perform(get("/" + INSTANCE + "/courses/" + COURSE + "/exercises"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].name", is(EXERCISE_A_NAME)))
               .andExpect(jsonPath("$[1].name", is(EXERCISE_B_NAME)));

        verify(exerciseService, times(1)).findAllBy(INSTANCE, COURSE);
        verifyNoMoreInteractions(exerciseService);
    }

    @Test
    public void listForStudentShouldReturnExercises() throws Exception {

        final List<Exercise> exercises = buildExeciseList();

        when(exerciseService.findAllBy(INSTANCE, STUDENT, COURSE)).thenReturn(exercises);

        mockMvc.perform(get("/" + INSTANCE + "/students/" + STUDENT + "/courses/" + COURSE + "/exercises"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].name", is(EXERCISE_A_NAME)))
               .andExpect(jsonPath("$[1].name", is(EXERCISE_B_NAME)));

        verify(exerciseService, times(1)).findAllBy(INSTANCE, STUDENT, COURSE);
        verifyNoMoreInteractions(exerciseService);
    }

    @Test
    public void readByExerciseAndCourseShouldReturnExercise() throws Exception {

        final Exercise exercise = new Exercise();
        exercise.setName(EXERCISE_A_NAME);

        when(exerciseService.findBy(INSTANCE, COURSE, EXERCISE)).thenReturn(exercise);

        mockMvc.perform(get("/" + INSTANCE + "/courses/" + COURSE + "/exercises/" + EXERCISE))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.name", is(EXERCISE_A_NAME)));

        verify(exerciseService, times(1)).findBy(INSTANCE, COURSE, EXERCISE);
        verifyNoMoreInteractions(exerciseService);
    }

    @Test
    public void readByExerciseAndCourseAndUserShouldReturnExercise() throws Exception {

        final Exercise exercise = new Exercise();
        exercise.setName(EXERCISE_A_NAME);

        when(exerciseService.findBy(INSTANCE, STUDENT, COURSE, EXERCISE)).thenReturn(exercise);

        mockMvc.perform(get("/" + INSTANCE + "/students/" + STUDENT + "/courses/" + COURSE + "/exercises/" + EXERCISE))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.name", is(EXERCISE_A_NAME)));

        verify(exerciseService, times(1)).findBy(INSTANCE, STUDENT, COURSE, EXERCISE);
        verifyNoMoreInteractions(exerciseService);
    }
}
