package br.com.phlimadev.simplified_picpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ExceptionDTO> handleApplicationException(ApplicationException ex) {
        return new ResponseEntity<>(new ExceptionDTO(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleIdNotFoundException(IdNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}