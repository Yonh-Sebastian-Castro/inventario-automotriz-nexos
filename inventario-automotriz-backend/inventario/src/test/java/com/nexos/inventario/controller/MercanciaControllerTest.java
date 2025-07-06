package com.nexos.inventario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexos.inventario.dto.MercanciaDTO;
import com.nexos.inventario.service.MercanciaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MercanciaController.class)
public class MercanciaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MercanciaService mercanciaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRegistrarMercancia() throws Exception {
        // Crear DTO simulado de entrada
        MercanciaDTO entrada = new MercanciaDTO();
        entrada.setNombre("Batería");
        entrada.setCantidad(3);
        entrada.setFechaIngreso(LocalDate.of(2025, 7, 6));
        entrada.setUsuarioRegistraId(1L);

        // Simular DTO de salida
        MercanciaDTO salida = new MercanciaDTO();
        salida.setId(1L);
        salida.setNombre("Batería");
        salida.setCantidad(3);
        salida.setFechaIngreso(entrada.getFechaIngreso());
        salida.setUsuarioRegistraId(1L);

        // Simular comportamiento del servicio
        Mockito.when(mercanciaService.registrar(Mockito.any(MercanciaDTO.class)))
                .thenReturn(salida);

        // Ejecutar petición POST
        mockMvc.perform(post("/api/mercancia")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nombre").value("Batería"))
                .andExpect(jsonPath("$.cantidad").value(3))
                .andExpect(jsonPath("$.usuarioRegistraId").value(1));
    }
}
