package co.unicauca.stockexchange.controller;


import co.unicauca.stockexchange.adapter.controller.ClientController;
import co.unicauca.stockexchange.commons.domain.Client;
import co.unicauca.stockexchange.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ClientControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void whenGetClients_thenReturnClientList() throws Exception {

        List<Client> clientList = Arrays.asList(
                new Client("C01", "John Doe","+57 3116548765"),
                new Client("C02", "Jane Doe","+57 3116544565")
        );
        when(clientController.getClients()).thenReturn(clientList);


        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/client")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].clientId").value("C01"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].clientName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phone").value("+57 3116548765"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].clientId").value("C02"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].clientName").value("Jane Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phone").value("+57 3116544565"))
                .andReturn();
    }

    @Test
    void whenFindClientById_thenReturnClient() throws Exception {
        Client client = new Client("C01", "John Doe","+57 3116548765");
        when(clientService.findClient("C01")).thenReturn(client);

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/client/C01")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientId").value("C01"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.clientName").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("+57 3116548765"))
                .andReturn();
    }

}
