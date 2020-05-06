package org.codejudge.sb.error.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends Exception {

    private HttpStatus httpStatus;

    public NotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
