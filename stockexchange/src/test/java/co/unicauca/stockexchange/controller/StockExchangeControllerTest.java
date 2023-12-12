package co.unicauca.stockexchange.controller;

import co.unicauca.stockexchange.adapter.controller.StockExchangeController;
import co.unicauca.stockexchange.commons.domain.Action;
import co.unicauca.stockexchange.service.StockExchangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StockExchangeControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private StockExchangeController stockExchangeController;

    @Mock
    private StockExchangeService stockExchangeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(stockExchangeController).build();
    }

    @Test
    void testGetActions() throws Exception {
        List<Action> actionList = Arrays.asList(
                new Action("A01", "Action 1", 10.0, 15.0),
                new Action("A02", "Action 2", 20.0, 25.0)
        );
        when(stockExchangeService.getActions()).thenReturn(actionList);

        this.mockMvc.perform(get("/stockExchange"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].actionId").value("A01"))
                .andExpect(jsonPath("$[0].actionName").value("Action 1"))
                .andExpect(jsonPath("$[1].actionId").value("A02"))
                .andExpect(jsonPath("$[1].actionName").value("Action 2"));
    }

    @Test
    void testFindAction() throws Exception {
        Action action = new Action("A01", "Action 1", 10.0, 15.0);
        when(stockExchangeService.findAction("A01")).thenReturn(action);

        this.mockMvc.perform(get("/stockExchange/A01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.actionId").value("A01"))
                .andExpect(jsonPath("$.actionName").value("Action 1"));
    }
}
