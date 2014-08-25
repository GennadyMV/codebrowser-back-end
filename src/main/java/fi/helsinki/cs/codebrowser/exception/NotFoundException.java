package fi.helsinki.cs.codebrowser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public final class NotFoundException extends RuntimeException { }
