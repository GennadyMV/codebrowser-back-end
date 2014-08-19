package fi.helsinki.cs.codebrowser.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TagTest {

    private Tag tag;

    @Before
    public void setUp() {

        tag = new Tag();
    }

    @Test
    public void canSetCourse() {

        final Course course = new Course();
        tag.setCourse(course);

        assertEquals(course, tag.getCourse());
    }

    @Test
    public void canSetStudent() {

        final Student student = new Student();
        tag.setStudent(student);

        assertEquals(student, tag.getStudent());
    }

    @Test
    public void canSetExercise() {

        final Exercise exercise = new Exercise();
        tag.setExercise(exercise);

        assertEquals(exercise, tag.getExercise());
    }

    @Test
    public void canSetSnapshot() {

        final Snapshot snapshot = new Snapshot();
        tag.setSnapshot(snapshot);

        assertEquals(snapshot, tag.getSnapshot());
    }

    @Test
    public void canSetTagName() {

        final TagName tagName = new TagName();
        tag.setTagName(tagName);

        assertEquals(tagName, tag.getTagName());
    }

    @Test
    public void equalsHandlesNull() {

        assertEquals(-1, tag.compareTo(null));
    }

    @Test
    public void equalsComparesTagNames() {
        final TagName tn1 = new TagName();
        tn1.setName("tag1");

        final TagName tn2 = new TagName();
        tn2.setName("tag2");

        final Tag t1 = new Tag();
        t1.setTagName(tn1);

        final Tag t2 = new Tag();
        t2.setTagName(tn2);

        int expectedResult = tn1.compareTo(tn2);
        assertEquals(expectedResult, t1.compareTo(t2));

        expectedResult = tn2.compareTo(tn1);
        assertEquals(expectedResult, t2.compareTo(t1));

        assertEquals(0, t1.compareTo(t1));
    }
}
