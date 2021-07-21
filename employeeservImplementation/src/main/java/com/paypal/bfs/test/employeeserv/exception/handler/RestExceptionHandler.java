package com.paypal.bfs.test.employeeserv.exception.handler;

import com.paypal.bfs.test.employeeserv.api.model.exception.RestExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestController
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<RestExceptionResponse> handleValidationException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest,
                                                                           HttpServletResponse httpServletResponse) {
        log.error("MethodArgumentNotValidException {}", e.getMessage(), e);
        List<String> messages = new ArrayList<>();
        for (ObjectError br : e.getBindingResult().getAllErrors()) {
            messages.add(br.getDefaultMessage());
        }
        RestExceptionResponse restExceptionResponse = RestExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.toString())
                .message(messages.toString())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(restExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestExceptionResponse> handleEntityNotFoundException(EntityNotFoundException ex, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.error("EntityNotFoundException {}", ex.getMessage(), ex);
        RestExceptionResponse restExceptionResponse = RestExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.toString())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(restExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RestExceptionResponse> handleException(RuntimeException ex, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.error("Exception {}", ex.getMessage(), ex);
        RestExceptionResponse restExceptionResponse = RestExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.toString())
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(restExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

