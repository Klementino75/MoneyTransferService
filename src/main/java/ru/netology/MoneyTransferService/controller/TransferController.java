package ru.netology.MoneyTransferService.controller;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.netology.MoneyTransferService.model.ConfirmOperation;
import ru.netology.MoneyTransferService.model.TransferModel;
import ru.netology.MoneyTransferService.model.TransferResponse;
import ru.netology.MoneyTransferService.service.TransferService;

@CrossOrigin("https://serp-ya.github.io/")
@AllArgsConstructor
@RestController
public class TransferController {
    private final TransferService service;

    @PostMapping("/transfer")
    public TransferResponse postTransfer(@RequestBody TransferModel transferModel) {
        return  service.postTransfer(transferModel);
    }

    @PostMapping("/confirmOperation")
    public TransferResponse postConfirmOperation(@RequestBody ConfirmOperation confirmOperation) {
        return  service.postConfirmOperation(confirmOperation);
    }
}