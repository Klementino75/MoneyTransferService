package ru.netology.MoneyTransferService.exception;

public class ErrorTransfer extends  RuntimeException {
    public ErrorTransfer(String msg) {
        super(msg);
    }
}