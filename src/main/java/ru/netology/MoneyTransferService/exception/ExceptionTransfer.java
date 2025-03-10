package ru.netology.MoneyTransferService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionTransfer {

    @ExceptionHandler(ErrorInputData.class)
    public ResponseEntity<String> handleErrorData(ErrorInputData e) {
        System.out.println("Exception: " + e.getMessage());
        return new ResponseEntity<>("Exception: 400 " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorTransfer.class)
    public ResponseEntity<String> handleErrorTransfer(ErrorTransfer e) {
        System.out.println("Exception: " + e.getMessage());
        return new ResponseEntity<>("Exception: 500 " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorConfirmation.class)
    public ResponseEntity<String> handleErrorConfirmation(ErrorConfirmation e) {
        System.out.println("Exception: " + e.getMessage());
        return new ResponseEntity<>("Exception: 500 " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}