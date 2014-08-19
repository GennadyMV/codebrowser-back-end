package fi.helsinki.cs.codebrowser.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

//Checkstyle forbids classes that begin with the string Abstract from being non-abstract.
//This is an intentional misspelling until a better solution is found.
public class AbstrctNamedPersistableTest {

    private static class Stub extends AbstractNamedPersistable { }

    private static final String NAME = "ANPname";

    private Stub stub;

    @Before
    public void setUp() {

        stub = new Stub();
    }

    @Test
    public void canSetName() {

        stub.setName(NAME);

        assertEquals(NAME, stub.getName());
    }
}
