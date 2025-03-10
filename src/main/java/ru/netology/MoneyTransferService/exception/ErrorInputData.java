package ru.netology.MoneyTransferService.exception;

public class ErrorInputData extends  RuntimeException {
    public ErrorInputData(String msg) {
        super(msg);
    }
}