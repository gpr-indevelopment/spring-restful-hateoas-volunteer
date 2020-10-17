package com.springrestfulhateoasvolunteer.volunteer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidVolunteerCreationException extends RuntimeException {

    public InvalidVolunteerCreationException() {
    }

    public InvalidVolunteerCreationException(String message) {
        super(message);
    }

    public InvalidVolunteerCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidVolunteerCreationException(Throwable cause) {
        super(cause);
    }

    public InvalidVolunteerCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
