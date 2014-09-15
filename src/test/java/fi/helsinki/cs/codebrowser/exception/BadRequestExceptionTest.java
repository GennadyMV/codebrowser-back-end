package fi.helsinki.cs.codebrowser.exception;

import org.junit.Test;

public final class BadRequestExceptionTest {

    @Test
    public void canConstruct() {

        final BadRequestException bre = new BadRequestException();
    }
}
