package ru.netology.MoneyTransferService.contoller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import static org.mockito.Mockito.times;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ru.netology.MoneyTransferService.controller.TransferController;
import ru.netology.MoneyTransferService.model.ConfirmOperation;
import ru.netology.MoneyTransferService.model.TransferAmount;
import ru.netology.MoneyTransferService.model.TransferModel;
import ru.netology.MoneyTransferService.model.TransferResponse;
import ru.netology.MoneyTransferService.repository.TransferRepository;
import ru.netology.MoneyTransferService.service.TransferService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransferController.class)
@DisplayName("Тесты с использованием Mockito")
class TransferControllerTest {
    private static final String END_POINT_PATH = "/transfer";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TransferService service;
    @MockBean
    private TransferRepository repository;
    TransferModel model = new TransferModel("2222222222222222",
            "1126",
            "123",
            "4444444444444444",
            new TransferAmount(1000, "RUR"));


    @DisplayName("Тест на запрос перевода, статус: 200 OK ")
    @Test
    void postTransferTestAddShouldReturn201Created() throws Exception {
        Mockito.when(service.postTransfer(model)).thenReturn(new TransferResponse("1"));
        String requestBody = objectMapper.writeValueAsString(model);
        mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(print());
    }

//    @DisplayName("Тест на запрос перевода, статус: 400 BadRequest")
//    @Test
//    public void testAddShouldReturn400BadRequest() throws Exception {
//        TransferModel model = new TransferModel(" ",
//                " ",
//                " ",
//                " ",
//                new TransferAmount(0, " "));
//
//        String requestBody = objectMapper.writeValueAsString(model);
//        mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
//                        .content(requestBody))
//                .andExpect(status().isBadRequest())
//                .andDo(print());
//    }

    @DisplayName("Тест, метод должен быть вызван ровно один раз")
    @Test
    void servicePostTransferTestMustOneTime() throws Exception {
        Mockito.when(service.postTransfer(model)).thenReturn(new TransferResponse("1"));
        String requestBody = objectMapper.writeValueAsString(model);
        mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(print());
        Mockito.verify(service, times(1)).postTransfer(model);
    }

    @DisplayName("Тест на запрос кода СМС, статус: 200 OK ")
    @Test
    void postConfirmOperationTestAddShouldReturn201Created() throws Exception {
        ConfirmOperation confirmOperation = new ConfirmOperation("1", "0000");
        Mockito.when(service.postConfirmOperation(confirmOperation)).thenReturn(new TransferResponse("1"));

        String requestBody = objectMapper.writeValueAsString(confirmOperation);
        mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andDo(print());
    }
}