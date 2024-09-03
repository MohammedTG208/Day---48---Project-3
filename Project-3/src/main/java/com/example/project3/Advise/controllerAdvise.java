package com.example.project3.Advise;

import com.example.project3.API.APIException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class controllerAdvise {

    @ExceptionHandler(value = APIException.class)
    public ResponseEntity ApiExceptionHandler(APIException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
