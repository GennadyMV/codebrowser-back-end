package fi.helsinki.cs.codebrowser.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoleTest {

    private Role role;

    @Before
    public void setUp() {

        role = new Role();
    }

    @Test
    public void canSetRolename() {

        role.setRolename("name");

        assertEquals("name", role.getRolename());
    }

    @Test
    public void canSetUsers() {

        final List<User> users = new ArrayList<>();

        role.setUsers(users);

        assertEquals(users, role.getUsers());
    }

}
