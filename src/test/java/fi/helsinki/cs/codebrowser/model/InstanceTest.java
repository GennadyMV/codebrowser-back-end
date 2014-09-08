package fi.helsinki.cs.codebrowser.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InstanceTest {

    private static final String NAME = "TestInstanceName";
    private static final String ID = "TestInstanceID";

    private Instance instance;

    @Before
    public void setUp() {

        instance = new Instance(ID, NAME);
    }

    @Test
    public void constructorSetsCorrectValues() {

        assertEquals(NAME, instance.getName());
        assertEquals(ID, instance.getId());
    }
}
