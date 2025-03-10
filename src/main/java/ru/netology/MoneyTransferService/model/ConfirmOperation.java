package ru.netology.MoneyTransferService.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class ConfirmOperation {
    private String operationId;
    private String code;

    public ConfirmOperation(){
    }

    public ConfirmOperation(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }

    @Override
    public String toString() {
        return "ConfirmOperationModel {" +
                "operationId = '" + operationId + '\'' +
                ", code = '" + code + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfirmOperation that = (ConfirmOperation) o;
        return Objects.equals(operationId, that.operationId) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationId, code);
    }
}