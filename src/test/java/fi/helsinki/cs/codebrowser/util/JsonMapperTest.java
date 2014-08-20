package fi.helsinki.cs.codebrowser.util;

import fi.helsinki.cs.codebrowser.model.Student;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonMapperTest {

    private static final String STUDENT_1_USERNAME = "a";
    private static final String STUDENT_2_USERNAME = "b";

    private JsonMapper mapper;

    @Before
    public void setUp() {

        mapper = new JsonMapper();
    }

    @Test
    public void readSubElementValueReadsSubElement() throws IOException {

        final String json = "{\"sub\":{\"username\":\"a\"}}";

        final Student student = mapper.readSubElementValue(json, Student.class, "sub");

        assertEquals(STUDENT_1_USERNAME, student.getUsername());
    }

    @Test
    public void readSubElementValueReadsCorrectSubElement() throws IOException {

        final String json = "{\"sub1\":{\"username\":\"a\"},\"sub2\":{\"username\":\"b\"}}";

        final Student student = mapper.readSubElementValue(json, Student.class, "sub2");

        assertEquals(STUDENT_2_USERNAME, student.getUsername());
    }

    @Test
    public void readSubElementValueReadsCorrectSubSubElement() throws IOException {

        final String json = "{\"sub1\":{\"sub2\":{\"username\":\"a\"}}}";

        final Student student = mapper.readSubElementValue(json, Student.class, "sub1", "sub2");

        assertEquals(STUDENT_1_USERNAME, student.getUsername());
    }

    @Test
    public void readSubElementValueToListReadsList() throws IOException {

        final String json = "{\"sub\":[{\"username\":\"a\"},{\"username\":\"b\"}]}";

        final List<Student> students = mapper.readSubElementValueToList(json, Student.class, "sub");

        assertEquals(STUDENT_1_USERNAME, students.get(0).getUsername());
        assertEquals(STUDENT_2_USERNAME, students.get(1).getUsername());
    }

    @Test
    public void readSubElementValueToListReadsCorrectList() throws IOException {

        final String json = "{\"sub1\":\"aaaa\",\"sub2\":[{\"username\":\"a\"},{\"username\":\"b\"}]}";

        final List<Student> students = mapper.readSubElementValueToList(json, Student.class, "sub2");

        assertEquals(STUDENT_1_USERNAME, students.get(0).getUsername());
        assertEquals(STUDENT_2_USERNAME, students.get(1).getUsername());
    }

    @Test
    public void readSubElementValueToListReadsCorrectSubElementList() throws IOException {

        final String json = "{\"sub1\":{\"sub2\":[{\"username\":\"a\"},{\"username\":\"b\"}]}}";

        final List<Student> students = mapper.readSubElementValueToList(json, Student.class, "sub1", "sub2");

        assertEquals(STUDENT_1_USERNAME, students.get(0).getUsername());
        assertEquals(STUDENT_2_USERNAME, students.get(1).getUsername());
    }

    @Test
    public void readValueToListReadsCorrectList() throws IOException {

        final String json = "[{\"username\":\"a\"},{\"username\":\"b\"}]";

        final List<Student> students = mapper.readValueToList(json, Student.class);

        assertEquals(STUDENT_1_USERNAME, students.get(0).getUsername());
        assertEquals(STUDENT_2_USERNAME, students.get(1).getUsername());
    }
}
