package co.unicauca.stockexchange.controller;

import co.unicauca.stockexchange.adapter.controller.PortfolioController;
import co.unicauca.stockexchange.commons.domain.Action;
import co.unicauca.stockexchange.service.PortfolioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PortfolioControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PortfolioController portfolioController;

    @Mock
    private PortfolioService portfolioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(portfolioController).build();
    }

    @Test
    void testGetActions() throws Exception {
        List<Action> actionList = Arrays.asList(
                new Action("A01", "Action 1", 10.0, 15.0),
                new Action("A02", "Action 2", 20.0, 25.0)
        );
        when(portfolioService.getActions("C01")).thenReturn(actionList);

        this.mockMvc.perform(get("/portfolio/actions/C01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].actionId").value("A01"))
                .andExpect(jsonPath("$[0].actionName").value("Action 1"))
                .andExpect(jsonPath("$[1].actionId").value("A02"))
                .andExpect(jsonPath("$[1].actionName").value("Action 2"));
    }

    @Test
    void testFindAction() throws Exception {
        Action action = new Action("A01", "Action 1", 10.0, 15.0);
        when(portfolioService.findAction("C01", "A01")).thenReturn(action);


        this.mockMvc.perform(get("/portfolio/action/C01/A01"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.actionId").value("A01"))
                .andExpect(jsonPath("$.actionName").value("Action 1"));
    }

    @Test
    void testAddAction() throws Exception {
        this.mockMvc.perform(post("/portfolio/addAction/C01/A01/80000/150000"))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyOrNullString()));


        verify(portfolioService, times(1)).addAction("C01", "A01", 80000.0, 150000.0);
    }

    @Test
    void testUpdateThresholds() throws Exception {
        this.mockMvc.perform(put("/portfolio/updateAction/C01/A01/85000/155000"))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyOrNullString()));

        verify(portfolioService, times(1)).updateThresholds("C01", "A01", 85000.0, 155000.0);
    }

    @Test
    void testDeleteAction() throws Exception {
        this.mockMvc.perform(delete("/portfolio/deleteAction/C01/A01"))
                .andExpect(status().isOk())
                .andExpect(content().string(isEmptyOrNullString()));

        verify(portfolioService, times(1)).deleteAction("C01", "A01");
    }
}
