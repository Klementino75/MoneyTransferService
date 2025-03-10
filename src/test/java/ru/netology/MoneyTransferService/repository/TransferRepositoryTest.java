package ru.netology.MoneyTransferService.repository;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.netology.MoneyTransferService.model.ConfirmOperation;
import ru.netology.MoneyTransferService.model.TransferAmount;
import ru.netology.MoneyTransferService.model.TransferModel;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тест Repository")
@SpringBootTest
class TransferRepositoryTest {
    @Autowired
    private TransferRepository repository;
    private static long suiteStartTime;
    private long testStartTime;
    public static final String OPERATION_ID = "1";
    private static final String CODE = "3344";
    public static final TransferModel TRANSFER_REQUEST = new TransferModel(
            "1111111111111111",
            "12/25",
            "123",
            "2222222222222222",
            new TransferAmount(3000, "RUR"));
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

    @Test
    void getOperationId() {
        int expected = 1;
        assertEquals(expected, repository.getOperationId());
    }

    @Test
    void addAndRemoveTransfer() {
        repository.addTransfer(OPERATION_ID, TRANSFER_REQUEST);
        TransferModel afterAdd = repository.removeTransfer(OPERATION_ID);
        assertEquals(TRANSFER_REQUEST, afterAdd);
    }

    @Test
    void addAndRemoveTransferNull() {
        TransferModel beforeAdd = repository.removeTransfer(OPERATION_ID);
        assertNull(beforeAdd);
    }

    @Test
    void addAndRemoveCode() {
        repository.addCode(OPERATION_ID, CODE);
        String afterAdd = repository.removeCode(OPERATION_ID);
        assertEquals(CODE, afterAdd);
    }

    @Test
    void addAndRemoveCodeNull() {
        String beforeAdd = repository.removeCode(OPERATION_ID);
        assertNull(beforeAdd);
    }
}