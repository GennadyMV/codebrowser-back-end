package fi.helsinki.cs.codebrowser.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class TagTest {

    private Tag tag;

    @Before
    public void setUp() {

        tag = new Tag();
    }

    @Test
    public void canSetInstanceId() {

        tag.setInstanceId("hy");

        assertEquals("hy", tag.getInstanceId());
    }

    @Test
    public void canSetStudentId() {

        tag.setStudentId("1");

        assertEquals("1", tag.getStudentId());
    }

    @Test
    public void canSetCourseId() {

        tag.setCourseId("2");

        assertEquals("2", tag.getCourseId());
    }

    @Test
    public void canSetExerciseId() {

        tag.setExerciseId("3");

        assertEquals("3", tag.getExerciseId());
    }

    @Test
    public void canSetName() {

        tag.setName("tagName");

        assertEquals("tagName", tag.getName());
    }

    @Test
    public void toStringIsSetCorrectly() {

        tag.setName("testName");

        assertEquals("testName", tag.toString());
    }
}
