package com.example.catalogoveiculos;

import com.example.catalogoveiculos.model.Veiculo;
import com.example.catalogoveiculos.repository.VeiculoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VeiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Veiculo veiculoExistente;

    @BeforeEach
    public void setup() {
        veiculoRepository.deleteAll();
        veiculoExistente = new Veiculo(null, "Toyota", "Corolla", 2021);
        veiculoExistente = veiculoRepository.save(veiculoExistente);
    }

    @Test
    public void deveListarTodosVeiculos() throws Exception {
        mockMvc.perform(get("/veiculos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deveBuscarVeiculoPorId() throws Exception {
        mockMvc.perform(get("/veiculos/{id}", veiculoExistente.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca", is("Toyota")))
                .andExpect(jsonPath("$.modelo", is("Corolla")))
                .andExpect(jsonPath("$.ano", is(2021)));
    }

    @Test
    public void deveRetornar404SeVeiculoNaoExistir() throws Exception {
        mockMvc.perform(get("/veiculos/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deveAdicionarNovoVeiculo() throws Exception {
        Veiculo novoVeiculo = new Veiculo(null, "Honda", "Civic", 2020);

        mockMvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(novoVeiculo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca", is("Honda")))
                .andExpect(jsonPath("$.modelo", is("Civic")))
                .andExpect(jsonPath("$.ano", is(2020)));
    }

    @Test
    public void deveAtualizarVeiculo() throws Exception {
        Veiculo veiculoAtualizado = new Veiculo(null, "Toyota", "Corolla", 2022);

        mockMvc.perform(put("/veiculos/{id}", veiculoExistente.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(veiculoAtualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca", is("Toyota")))
                .andExpect(jsonPath("$.modelo", is("Corolla")))
                .andExpect(jsonPath("$.ano", is(2022)));
    }

    @Test
    public void deveRemoverVeiculo() throws Exception {
        mockMvc.perform(delete("/veiculos/{id}", veiculoExistente.getId()))
                .andExpect(status().isNoContent());

        Optional<Veiculo> veiculoRemovido = veiculoRepository.findById(veiculoExistente.getId());
        assert (veiculoRemovido.isEmpty());
    }

    @Test
    public void deveRetornar404AoRemoverVeiculoNaoExistente() throws Exception {
        mockMvc.perform(delete("/veiculos/{id}", 999))
                .andExpect(status().isNotFound());
    }
}
