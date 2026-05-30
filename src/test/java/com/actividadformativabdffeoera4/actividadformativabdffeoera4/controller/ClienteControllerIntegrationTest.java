package com.actividadformativabdffeoera4.actividadformativabdffeoera4.controller;

import com.actividadformativabdffeoera4.actividadformativabdffeoera4.entity.Cliente;
import com.actividadformativabdffeoera4.actividadformativabdffeoera4.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ClienteControllerIntegrationTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    void getClientes_devuelve200YLista() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Cliente API");
        cliente.setEmpresa("Empresa API");

        when(clienteService.listar()).thenReturn(List.of(cliente));

        mockMvc.perform(get("/api/clientes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Cliente API"));
    }
}
