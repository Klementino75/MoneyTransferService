package ru.netology.MoneyTransferService.logger;

import ru.netology.MoneyTransferService.model.ConfirmOperation;
import ru.netology.MoneyTransferService.model.TransferModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecordLog {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static volatile RecordLog INSTANCE = null;

    private RecordLog() {
    }

    public static RecordLog getInstance() {
        if (INSTANCE == null) {
            synchronized (RecordLog.class) {
                if (INSTANCE == null)
                    INSTANCE = new RecordLog();
            }
        }
        return INSTANCE;
    }

    public static String logTransferModel(TransferModel model, Integer amountRub, double commissionRub, String codeUuid) {
        return " [" + dtf.format(LocalDateTime.now()) + "] <" +
                "Перевод с карты: " + model.getCardFromNumber() +
                " на карту: " + model.getCardToNumber() +
                " в сумме: " + amountRub + " руб, " +
                "комиссия за перевод: " + commissionRub + " руб, " +
                "id: " + codeUuid +
                ">\n";
    }

    public String logConfirmOperation(ConfirmOperation confirmOperation, String codeUuid) {
        return String.format("[Успешно: код операции: '%s']\n", codeUuid);
    }

    public String logError(String stringUuid) {
        return String.format("[ERROR, код операции: '%s']\n", stringUuid);
    }
}