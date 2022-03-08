package musinsa.api.point.exception;

import musinsa.api.point.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(PointException.class)
    public ResponseEntity<ErrorResponse> handleNoPointException(PointException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getStatusEnum());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getStatusEnum().getStatusCode()));
    }
}
