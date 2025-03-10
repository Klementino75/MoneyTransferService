package ru.netology.MoneyTransferService.service;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.netology.MoneyTransferService.model.ConfirmOperation;
import ru.netology.MoneyTransferService.model.TransferAmount;
import ru.netology.MoneyTransferService.model.TransferModel;
import ru.netology.MoneyTransferService.model.TransferResponse;
import ru.netology.MoneyTransferService.repository.TransferRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест Service")
@SpringBootTest
class TransferServiceTest {
    @Autowired
    private TransferService service;
    @Autowired
    private TransferRepository repository;
    private static long suiteStartTime;
    private long testStartTime;
    public static final String OPERATION_ID = "2";
    private static final String CODE = "6765";
    public static final TransferModel TRANSFER_REQUEST = new TransferModel(
            "1111111111111111",
            "12/25",
            "123",
            "2222222222222222",
            new TransferAmount(5000_00, "RUR"));
    public static final ConfirmOperation CONFIRM_OPERATION_REQUEST = new ConfirmOperation(OPERATION_ID, CODE);

    @BeforeAll
    public static void initSuite() {
        System.out.println("Тест запущен");
        suiteStartTime = System.nanoTime();
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("Тест выполнен: " + (System.nanoTime() - suiteStartTime) / 1000000 + " ms");
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Запуск нового теста");
        testStartTime = System.nanoTime();
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Тест выполнен:" + (System.nanoTime() - testStartTime) / 1000000 + " ms");
    }

    @DisplayName("Тест на перевод денежных средств и получения id")
    @Test
    void transfer() {
        TransferResponse expected = new TransferResponse(OPERATION_ID);
        TransferResponse actual = service.postTransfer(TRANSFER_REQUEST);
        assertEquals(expected, actual);
    }

    @DisplayName("Тест на получения кода подтверждения и id")
    @Test
    void confirmOperation() {
        repository.addTransfer("2", TRANSFER_REQUEST);
        TransferResponse expected = new TransferResponse(OPERATION_ID);
        TransferResponse actual = service.postConfirmOperation(CONFIRM_OPERATION_REQUEST);
        assertEquals(expected, actual);
    }
}