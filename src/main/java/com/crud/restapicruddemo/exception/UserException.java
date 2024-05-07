package com.crud.restapicruddemo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UserException {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;

    public UserException(Throwable throwable, String message, HttpStatus httpStatus) {
        this.throwable = throwable;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
