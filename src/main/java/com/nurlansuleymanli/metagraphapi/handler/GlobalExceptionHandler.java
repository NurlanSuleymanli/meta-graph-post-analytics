package com.nurlansuleymanli.metagraphapi.handler;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeignException(FeignException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "An error occurred while connecting to the Meta API." +
                " Please check that your Access Token or Instagram User ID is valid and has not expired."));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","No sharing information was found from the Instagram account or the API returned an empty response."));
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<?> handleDateTimeException(DateTimeParseException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","An error occurred while parsing the date format. Please check the structure of the incoming date."));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message","An unexpected internal system error has occurred. Please try again later."));
    }

}
