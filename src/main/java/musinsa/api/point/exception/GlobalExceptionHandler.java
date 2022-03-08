package musinsa.api.point.exception;

import musinsa.api.point.response.ErrorResponse;
import musinsa.common.config.StatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PointException.class)
    public ResponseEntity<ErrorResponse> handleNoPointException(PointException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getStatusEnum());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(exception.getStatusEnum().getStatusCode()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorResponse errorResponse = new ErrorResponse(StatusEnum.VALID_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
