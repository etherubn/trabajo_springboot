package com.apirest.mitocode.ruben.exception;

import com.apirest.mitocode.ruben.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(NotFoundException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(404);
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setUri(request.getRequestURI());
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> methodArgumentNotValidException(MethodArgumentNotValidException e,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        e.getBindingResult().getAllErrors().stream().forEach(
                error -> {
                    map.put(((FieldError) error).getField(), error.getDefaultMessage());
                }
        );

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(409);
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setUri(request.getRequestURI());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setError(map);

        return new ResponseEntity<>(map, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> entityAlreadyExist(EntityAlreadyExistException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());
        errorResponse.setStatusCode(409);
        errorResponse.setDateTime(LocalDateTime.now());
        errorResponse.setUri(request.getRequestURI());
        errorResponse.setMessage(error.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BussinesException.class)
    public ResponseEntity<CustomErrorRecord> businesExceptionHandler(BussinesException exception, WebRequest request){
        CustomErrorRecord errorResponse = new CustomErrorRecord(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }
}
