package fi.helsinki.cs.codebrowser.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class ErrorTest {

    @Test
    public void canSetMessage() {

        final Error error = new Error("message");

        assertEquals("message", error.getError());
    }
}
