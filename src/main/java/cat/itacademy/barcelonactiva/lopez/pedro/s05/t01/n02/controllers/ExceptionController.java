package cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.controllers;

import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.model.exception.FlowerDoesNotExistException;
import cat.itacademy.barcelonactiva.lopez.pedro.s05.t01.n02.controllers.util.Message;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
@SuppressWarnings("unused")
public class ExceptionController {

    @ExceptionHandler(FlowerDoesNotExistException.class)
    // docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ExceptionHandler.html
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Message> flowerNotFoundExceptionHandler(FlowerDoesNotExistException exception, WebRequest request) {

        return new ResponseEntity<>(new Message(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getDescription(false), new Date()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<List<Message>> fieldNotValidExceptionHandler(MethodArgumentNotValidException exception) {

        List<Message> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(error ->
                errors.add(new Message(HttpStatus.NOT_ACCEPTABLE.value(), error.getDefaultMessage(), ((FieldError) error).getField(), new Date())));

        return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Message> HttpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException exception, WebRequest request) {

        return new ResponseEntity<>(new Message(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request.getDescription(false), new Date()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServerException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Message> serverExceptionHandler(Exception exception, WebRequest request) {

        return new ResponseEntity<>(new Message(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), request.getDescription(false), new Date()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
