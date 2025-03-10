package ru.netology.MoneyTransferService.repository;

import org.springframework.stereotype.Repository;

import ru.netology.MoneyTransferService.model.TransferModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TransferRepository {
    private final Map<String, TransferModel> transfers = new ConcurrentHashMap<>();
    private final Map<String, String> codes = new ConcurrentHashMap<>();
    private final AtomicInteger operationId = new AtomicInteger();

    public int getOperationId() {
        return operationId.incrementAndGet();
    }

    public void addTransfer(String id, TransferModel transferRequest) {
        transfers.put(id, transferRequest);
    }

    public void addCode(String id, String code) {
        codes.put(id, code);
    }

    public TransferModel removeTransfer(String id) {
        return transfers.remove(id);
    }

    public String removeCode(String id) {
        return codes.remove(id);
    }
}