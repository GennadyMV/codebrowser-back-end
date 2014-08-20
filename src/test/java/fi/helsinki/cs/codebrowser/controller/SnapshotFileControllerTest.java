package fi.helsinki.cs.codebrowser.controller;

import fi.helsinki.cs.codebrowser.app.App;
import fi.helsinki.cs.codebrowser.model.SnapshotFile;
import fi.helsinki.cs.codebrowser.service.SnapshotFileService;
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
public class SnapshotFileControllerTest {

    private static final String SNAPSHOTFILE_1_ID = "snapshot1Id";
    private static final String SNAPSHOTFILE_1_PATH = "snapshotFile1Path";
    private static final String SNAPSHOTFILE_1_NAME = "snapshot1Name";

    private static final String SNAPSHOTFILE_2_ID = "snapshot2Id";
    private static final String SNAPSHOTFILE_2_PATH = "snapshotFile2Path";
    private static final String SNAPSHOTFILE_2_NAME = "snapshot2Name";

    private static final String STUDENT = "testStudentId";
    private static final String COURSE = "testCourseId";
    private static final String EXERCISE = "testExerciseId";
    private static final String SNAPSHOT = "testSnapshotId";
    private static final String FILE = "testFileId";
    private static final String CONTENT = "testFileContent";

    private static final String BASE_URL_1 = "/students/" + STUDENT + "/courses/" + COURSE + "/exercises/" + EXERCISE + "/snapshots/" + SNAPSHOT + "/files";
    private static final String BASE_URL_2 = "/courses/" + COURSE + "/exercises/" + EXERCISE + "/students/" + STUDENT + "/snapshots/" + SNAPSHOT + "/files";

    @Mock
    private SnapshotFileService snapshotFileService;

    @InjectMocks
    private SnapshotFileController snapshotFileController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(snapshotFileController).build();
    }

    public List<SnapshotFile> buildSnapshotFiles() {
        final List<SnapshotFile> snapshotFiles = new ArrayList<>();

        snapshotFiles.add(new SnapshotFile(SNAPSHOTFILE_1_ID, SNAPSHOTFILE_1_NAME, SNAPSHOTFILE_1_PATH));
        snapshotFiles.add(new SnapshotFile(SNAPSHOTFILE_2_ID, SNAPSHOTFILE_2_NAME, SNAPSHOTFILE_2_PATH));

        return snapshotFiles;
    }

    @Test
    public void listReturnsSnapshotFilesForPathStartingWithStudent() throws Exception {

        final List<SnapshotFile> snapshotFiles = buildSnapshotFiles();

        when(snapshotFileService.findAllBy(STUDENT, COURSE, EXERCISE, SNAPSHOT)).thenReturn(snapshotFiles);

        mockMvc.perform(get(BASE_URL_1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(SNAPSHOTFILE_1_ID)))
                .andExpect(jsonPath("$[1].id", is(SNAPSHOTFILE_2_ID)));

        verify(snapshotFileService, times(1)).findAllBy(STUDENT, COURSE, EXERCISE, SNAPSHOT);
        verifyNoMoreInteractions(snapshotFileService);
    }

    @Test
    public void listReturnsSnapshotFilesForPathStartingWithCourse() throws Exception {

        final List<SnapshotFile> snapshotFiles = buildSnapshotFiles();

        when(snapshotFileService.findAllBy(STUDENT, COURSE, EXERCISE, SNAPSHOT)).thenReturn(snapshotFiles);

        mockMvc.perform(get(BASE_URL_2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(SNAPSHOTFILE_1_ID)))
                .andExpect(jsonPath("$[1].id", is(SNAPSHOTFILE_2_ID)));

        verify(snapshotFileService, times(1)).findAllBy(STUDENT, COURSE, EXERCISE, SNAPSHOT);
        verifyNoMoreInteractions(snapshotFileService);
    }

    @Test
    public void readReturnsSnapshotFileForPathStartingWithStudent() throws Exception {

        final SnapshotFile snapshotFile = new SnapshotFile(SNAPSHOTFILE_1_ID, SNAPSHOTFILE_1_NAME, SNAPSHOTFILE_1_PATH);

        when(snapshotFileService.findBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE)).thenReturn(snapshotFile);

        mockMvc.perform(get(BASE_URL_1 + "/" + FILE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(SNAPSHOTFILE_1_ID)))
                .andExpect(jsonPath("$.filepath", is(SNAPSHOTFILE_1_PATH)));

        verify(snapshotFileService, times(1)).findBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE);
        verifyNoMoreInteractions(snapshotFileService);
    }

    @Test
    public void readReturnsSnapshotFileForPathStartingWithCourse() throws Exception {

        final SnapshotFile snapshotFile = new SnapshotFile(SNAPSHOTFILE_1_ID, SNAPSHOTFILE_1_NAME, SNAPSHOTFILE_1_PATH);

        when(snapshotFileService.findBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE)).thenReturn(snapshotFile);

        mockMvc.perform(get(BASE_URL_2 + "/" + FILE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(SNAPSHOTFILE_1_ID)))
                .andExpect(jsonPath("$.filepath", is(SNAPSHOTFILE_1_PATH)));

        verify(snapshotFileService, times(1)).findBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE);
        verifyNoMoreInteractions(snapshotFileService);
    }

    @Test
    public void readContentReturnsContentForPathStartingWithStudent() throws Exception {

        when(snapshotFileService.findContentBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE)).thenReturn(CONTENT);

        mockMvc.perform(get(BASE_URL_1 + "/" + FILE + "/content"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string(CONTENT));

        verify(snapshotFileService, times(1)).findContentBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE);
        verifyNoMoreInteractions(snapshotFileService);
    }

    @Test
    public void readContentReturnsContentForPathStartingWithCourse() throws Exception {

        when(snapshotFileService.findContentBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE)).thenReturn(CONTENT);

        mockMvc.perform(get(BASE_URL_2 + "/" + FILE + "/content"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_PLAIN))
                .andExpect(content().string(CONTENT));

        verify(snapshotFileService, times(1)).findContentBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE);
        verifyNoMoreInteractions(snapshotFileService);
    }
}
