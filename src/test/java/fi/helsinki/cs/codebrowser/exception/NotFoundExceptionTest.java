package fi.helsinki.cs.codebrowser.exception;

import org.junit.Test;

public final class NotFoundExceptionTest {

    @Test
    public void canConstruct() {

        final NotFoundException nfe = new NotFoundException();
    }
}
