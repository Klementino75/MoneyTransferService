package ru.netology.MoneyTransferService.model;

public record TransferAmount(Integer value, String currency) {

    @Override
    public String toString() {
        return "TransferAmount {" +
                "value = " + value +
                ", currency = '" + currency + '\'' +
                '}';
    }
}