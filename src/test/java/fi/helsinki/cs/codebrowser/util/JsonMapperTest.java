package fi.helsinki.cs.codebrowser.util;

import fi.helsinki.cs.codebrowser.model.Student;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class JsonMapperTest {

    private static final String STUDENT_A_USERNAME = "Student A";
    private static final String STUDENT_B_USERNAME = "Student B";

    private JsonMapper mapper;

    @Before
    public void setUp() {

        mapper = new JsonMapper();
    }

    @Test
    public void readSubElementValueReadsSubElement() throws IOException {

        final String json = "{\"sub\":{\"username\":\"Student A\"}}";

        final Student student = mapper.readSubElementValue(json, Student.class, "sub");

        assertEquals(STUDENT_A_USERNAME, student.getUsername());
    }

    @Test
    public void readSubElementValueReadsCorrectSubElement() throws IOException {

        final String json = "{\"sub1\":{\"username\":\"Student A\"},\"sub2\":{\"username\":\"Student B\"}}";

        final Student student = mapper.readSubElementValue(json, Student.class, "sub2");

        assertEquals(STUDENT_B_USERNAME, student.getUsername());
    }

    @Test
    public void readSubElementValueReadsCorrectSubSubElement() throws IOException {

        final String json = "{\"sub1\":{\"sub2\":{\"username\":\"Student A\"}}}";

        final Student student = mapper.readSubElementValue(json, Student.class, "sub1", "sub2");

        assertEquals(STUDENT_A_USERNAME, student.getUsername());
    }

    @Test
    public void readSubElementValueToListReadsList() throws IOException {

        final String json = "{\"sub\":[{\"username\":\"Student A\"},{\"username\":\"Student B\"}]}";

        final List<Student> students = mapper.readSubElementValueToList(json, Student.class, "sub");

        assertEquals(STUDENT_A_USERNAME, students.get(0).getUsername());
        assertEquals(STUDENT_B_USERNAME, students.get(1).getUsername());
    }

    @Test
    public void readSubElementValueToListReadsCorrectList() throws IOException {

        final String json = "{\"sub1\":\"aaaa\",\"sub2\":[{\"username\":\"Student A\"},{\"username\":\"Student B\"}]}";

        final List<Student> students = mapper.readSubElementValueToList(json, Student.class, "sub2");

        assertEquals(STUDENT_A_USERNAME, students.get(0).getUsername());
        assertEquals(STUDENT_B_USERNAME, students.get(1).getUsername());
    }

    @Test
    public void readSubElementValueToListReadsCorrectSubElementList() throws IOException {

        final String json = "{\"sub1\":{\"sub2\":[{\"username\":\"Student A\"},{\"username\":\"Student B\"}]}}";

        final List<Student> students = mapper.readSubElementValueToList(json, Student.class, "sub1", "sub2");

        assertEquals(STUDENT_A_USERNAME, students.get(0).getUsername());
        assertEquals(STUDENT_B_USERNAME, students.get(1).getUsername());
    }
}
