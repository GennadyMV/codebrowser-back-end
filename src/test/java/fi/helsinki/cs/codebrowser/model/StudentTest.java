package fi.helsinki.cs.codebrowser.model;

import org.apache.commons.codec.binary.Base64;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static org.junit.Assert.*;

public final class StudentTest {

    private static final String USERNAME = "studentUsername";
    private static final String PLAIN_ID = "studentID";
    private static final String FIRSTNAME = "firstName";
    private static final String LASTNAME = "lastName";

    private Student student;

    @Before
    public void setUp() {

        student = new Student();
    }

    @Test
    public void canSetUsername() {

        student.setUsername(USERNAME);

        assertEquals(USERNAME, student.getUsername());
    }

    @Test
    public void setLoginSetsUsername() {

        student.setLogin(USERNAME);

        assertEquals(USERNAME, student.getUsername());
    }

    @Test
    public void getNameReturnsConcatenatedFirstAndLastName() {

        student.setFirstName(FIRSTNAME);
        student.setLastName(LASTNAME);

        assertEquals(FIRSTNAME + " " + LASTNAME, student.getName());
    }

    @Test
    public void idIsSetBasedOnUsername() {

        final String expectedId = Base64.encodeBase64URLSafeString(USERNAME.getBytes());
        student.setUsername(USERNAME);

        assertEquals(expectedId, student.getId());
    }

    @Test
    public void canSetPlainId() {

        student.setPlainId(PLAIN_ID);

        assertEquals(PLAIN_ID, student.getPlainId());
    }

    @Test
    public void hashCodeIsNotSameForTwoUsersWithDifferentUsernames() {

        final Student student1 = new Student();
        student1.setUsername(USERNAME);

        final Student student2 = new Student();
        student2.setUsername("notSame");

        assertThat(student1.hashCode(), is(not(student2.hashCode())));
    }

    @Test
    public void hashCodeIsSameForTwoUsersWithSameUsernames() {

        final Student student1 = new Student();
        student1.setUsername(USERNAME);

        final Student student2 = new Student();
        student2.setUsername(USERNAME);

        assertEquals(student1.hashCode(), student2.hashCode());
    }

    @Test
    public void studentIsNotEqualToNull() {

        assertFalse(student.equals(null));
    }

    @Test
    public void studentIsNotEqualToObjectOfAnotherType() {

        assertFalse(student.equals(new String("")));
    }

    @Test
    public void studentIsNotEqualToStudentWithOtherUsername() {

        student.setUsername(USERNAME);

        final Student other = new Student();
        other.setUsername("NotSame");

        assertFalse(student.equals(other));
    }

    @Test
    public void studentIsEqualToStudentWithSameUsername() {

        student.setUsername(USERNAME);

        final Student other = new Student();
        other.setUsername(USERNAME);

        assertTrue(student.equals(other));
    }

    @Test
    public void studentWithNullUsernameIsNotEqualToStudentWithNonNullUsername() {

        final Student other = new Student();
        other.setUsername(USERNAME);

        assertFalse(student.equals(other));
    }

    @Test
    public void studentWithNonNullUsernameIsNotEqualToStudentWithNullUsername() {

        student.setUsername(USERNAME);

        final Student other = new Student();

        assertFalse(student.equals(other));
    }

    @Test
    public void studentWithNullUsernameIsEqualToStudentWithNullUsername() {

        final Student other = new Student();

        assertTrue(student.equals(other));
    }
}
