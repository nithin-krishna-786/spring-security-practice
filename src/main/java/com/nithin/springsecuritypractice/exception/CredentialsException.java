package com.nithin.springsecuritypractice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CredentialsException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
