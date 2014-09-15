package fi.helsinki.cs.codebrowser.exception;

import org.junit.Test;

public class BadRequestExceptionTest {

    @Test
    public void canConstruct() {

        final BadRequestException bre = new BadRequestException();
    }
}
