package ru.netology.MoneyTransferService;

import org.json.JSONException;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ru.netology.MoneyTransferService.model.ConfirmOperation;
import ru.netology.MoneyTransferService.model.TransferAmount;
import ru.netology.MoneyTransferService.model.TransferModel;
import ru.netology.MoneyTransferService.model.TransferResponse;

import java.util.Objects;

@Testcontainers
@DisplayName("Проверка REST-SERVICE")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourseworkRestCardTransferApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;
    private static final String ENDPOINT_TRANSFER = "/transfer";
    private static final String ENDPOINT_CONFIRM = "/confirmOperation";
    private static final String HOST = "http://localhost:";
    private static final int PORT = 5500;
    public static final String OPERATION_ID = "1";
    private static final String CODE = "1234";
    private static Integer portRandom;
    public static final TransferModel TRANSFER_REQUEST = new TransferModel(
            "1111111111111111",
            "12/25",
            "123",
            "2222222222222222",
            new TransferAmount(5000, "RUR"));
    public static final ConfirmOperation CONFIRM_OPERATION_REQUEST =
            new ConfirmOperation(OPERATION_ID, CODE);
    @Container
    private final static GenericContainer<?> container = new GenericContainer<>("myapp").
            withExposedPorts(PORT);

    @BeforeAll
    public static void setUp() {
        portRandom = container.getMappedPort(PORT);
    }

    @DisplayName("Тест на ответ REST-SERVICE. Метод postTransfer() №1")
    @Test
    void contextLoadsTestOne() throws Exception {
        ResponseEntity<Object> forTransfer = restTemplate.postForEntity(HOST + portRandom +
                ENDPOINT_TRANSFER, TRANSFER_REQUEST, Object.class);
        String expected = new JSONObject(Objects.requireNonNull(forTransfer.getBody()).toString())
                .get("operationId").toString();
        Assertions.assertEquals(OPERATION_ID, expected);
    }

    @DisplayName("Тест на ответ REST-SERVICE. Метод postTransfer() №2")
    @Test
    void contextLoadsTestTwo() {
        TransferResponse transferResponse = new TransferResponse("2");
        String expected = "{\"operationId\":\"2\"}";
        ResponseEntity<String> actual = restTemplate.postForEntity(HOST +
                portRandom + ENDPOINT_TRANSFER, transferResponse, String.class);
        Assertions.assertEquals(expected, actual.getBody());
    }

    @DisplayName("Тест на ответ REST-SERVICE. Метод postConfirmOperation()")
    @Test
    void contextLoadsConfirmOperation() throws JSONException {
        ResponseEntity<Object> forConfirmOperation = restTemplate.postForEntity(HOST + portRandom +
                ENDPOINT_CONFIRM, CONFIRM_OPERATION_REQUEST, Object.class);
        String expected = new JSONObject(Objects.requireNonNull(forConfirmOperation.getBody()).toString())
                .get("operationId").toString();
        Assertions.assertEquals(OPERATION_ID, expected);
    }
}