package ru.nsu.virtual_meetings.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handleInDBException(AlreadyExistException alreadyExistException) {
        return new ExceptionResponse(alreadyExistException.getMessage());
    }

    @ExceptionHandler(IncorrectInputException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handleInputException(IncorrectInputException incorrectInputException) {
        return new ExceptionResponse(incorrectInputException.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handleNotFoundException(NotFoundException notFoundException) {
        return new ExceptionResponse(notFoundException.getMessage());
    }

}
