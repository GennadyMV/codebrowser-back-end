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
public final class SnapshotFileControllerTest {

    private static final String SNAPSHOTFILE_A_ID = "snapshotFileA";
    private static final String SNAPSHOTFILE_A_PATH = "snapshotFileAPath";
    private static final String SNAPSHOTFILE_A_NAME = "snapshotFileAName";

    private static final String SNAPSHOTFILE_B_ID = "snapshotFileB";
    private static final String SNAPSHOTFILE_B_PATH = "snapshotFileAPath";
    private static final String SNAPSHOTFILE_B_NAME = "snapshotFileBName";

    private static final String STUDENT = "studentID";
    private static final String COURSE = "courseID";
    private static final String EXERCISE = "exerciseID";
    private static final String SNAPSHOT = "snapshotID";
    private static final String FILE = "fileID";
    private static final String CONTENT = "fileContent";

    private static final String BASE_URL_A = "/students/" + STUDENT + "/courses/" + COURSE + "/exercises/" + EXERCISE + "/snapshots/" + SNAPSHOT + "/files";
    private static final String BASE_URL_B = "/courses/" + COURSE + "/exercises/" + EXERCISE + "/students/" + STUDENT + "/snapshots/" + SNAPSHOT + "/files";

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

        snapshotFiles.add(new SnapshotFile(SNAPSHOTFILE_A_ID, SNAPSHOTFILE_A_NAME, SNAPSHOTFILE_A_PATH));
        snapshotFiles.add(new SnapshotFile(SNAPSHOTFILE_B_ID, SNAPSHOTFILE_B_NAME, SNAPSHOTFILE_B_PATH));

        return snapshotFiles;
    }

    @Test
    public void listReturnsSnapshotFilesForPathStartingWithStudent() throws Exception {

        final List<SnapshotFile> snapshotFiles = buildSnapshotFiles();

        when(snapshotFileService.findAllBy(STUDENT, COURSE, EXERCISE, SNAPSHOT)).thenReturn(snapshotFiles);

        mockMvc.perform(get(BASE_URL_A))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].id", is(SNAPSHOTFILE_A_ID)))
               .andExpect(jsonPath("$[1].id", is(SNAPSHOTFILE_B_ID)));

        verify(snapshotFileService, times(1)).findAllBy(STUDENT, COURSE, EXERCISE, SNAPSHOT);
        verifyNoMoreInteractions(snapshotFileService);
    }

    @Test
    public void listReturnsSnapshotFilesForPathStartingWithCourse() throws Exception {

        final List<SnapshotFile> snapshotFiles = buildSnapshotFiles();

        when(snapshotFileService.findAllBy(STUDENT, COURSE, EXERCISE, SNAPSHOT)).thenReturn(snapshotFiles);

        mockMvc.perform(get(BASE_URL_B))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(2)))
               .andExpect(jsonPath("$[0].id", is(SNAPSHOTFILE_A_ID)))
               .andExpect(jsonPath("$[1].id", is(SNAPSHOTFILE_B_ID)));

        verify(snapshotFileService, times(1)).findAllBy(STUDENT, COURSE, EXERCISE, SNAPSHOT);
        verifyNoMoreInteractions(snapshotFileService);
    }

    @Test
    public void readReturnsSnapshotFileForPathStartingWithStudent() throws Exception {

        final SnapshotFile snapshotFile = new SnapshotFile(SNAPSHOTFILE_A_ID, SNAPSHOTFILE_A_NAME, SNAPSHOTFILE_A_PATH);

        when(snapshotFileService.findBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE)).thenReturn(snapshotFile);

        mockMvc.perform(get(BASE_URL_A + "/" + FILE))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.id", is(SNAPSHOTFILE_A_ID)));

        verify(snapshotFileService, times(1)).findBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE);
        verifyNoMoreInteractions(snapshotFileService);
    }

    @Test
    public void readReturnsSnapshotFileForPathStartingWithCourse() throws Exception {

        final SnapshotFile snapshotFile = new SnapshotFile(SNAPSHOTFILE_A_ID, SNAPSHOTFILE_A_NAME, SNAPSHOTFILE_A_PATH);

        when(snapshotFileService.findBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE)).thenReturn(snapshotFile);

        mockMvc.perform(get(BASE_URL_B + "/" + FILE))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.id", is(SNAPSHOTFILE_A_ID)));

        verify(snapshotFileService, times(1)).findBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE);
        verifyNoMoreInteractions(snapshotFileService);
    }

    @Test
    public void readContentReturnsContentForPathStartingWithStudent() throws Exception {

        when(snapshotFileService.findContentBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE)).thenReturn(CONTENT);

        mockMvc.perform(get(BASE_URL_A + "/" + FILE + "/content"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.TEXT_PLAIN))
               .andExpect(content().string(CONTENT));

        verify(snapshotFileService, times(1)).findContentBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE);
        verifyNoMoreInteractions(snapshotFileService);
    }

    @Test
    public void readContentReturnsContentForPathStartingWithCourse() throws Exception {

        when(snapshotFileService.findContentBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE)).thenReturn(CONTENT);

        mockMvc.perform(get(BASE_URL_B + "/" + FILE + "/content"))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.TEXT_PLAIN))
               .andExpect(content().string(CONTENT));

        verify(snapshotFileService, times(1)).findContentBy(STUDENT, COURSE, EXERCISE, SNAPSHOT, FILE);
        verifyNoMoreInteractions(snapshotFileService);
    }
}
