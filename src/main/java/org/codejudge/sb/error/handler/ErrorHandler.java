package org.codejudge.sb.error.handler;

import org.codejudge.sb.error.exception.CustomException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(0)
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, e.getHttpStatus());
    }
//
//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<?> handleGenericException(Exception e) {
//        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
