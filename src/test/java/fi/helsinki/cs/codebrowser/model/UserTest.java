package fi.helsinki.cs.codebrowser.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final  class UserTest {

    private User user;

    @Before
    public void setUp() {

        user = new User();
    }

    @Test
    public void canSetUsername() {

        user.setUsername("asd123");

        assertEquals("asd123", user.getUsername());
    }

    @Test
    public void canSetPassword() {

        user.setPassword("fgh456");

        assertEquals("fgh456", user.getPassword());
    }

    @Test
    public void canSetRoles() {

        final List<Role> roles = new ArrayList<>();

        user.setRoles(roles);

        assertEquals(roles, user.getRoles());
    }
}
