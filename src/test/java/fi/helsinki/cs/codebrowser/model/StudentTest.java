package fi.helsinki.cs.codebrowser.model;

import org.apache.commons.codec.binary.Base64;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
