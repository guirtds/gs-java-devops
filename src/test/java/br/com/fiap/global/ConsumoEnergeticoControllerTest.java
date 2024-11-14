package br.com.fiap.global;

import br.com.fiap.global.controller.ConsumoEnergeticoController;
import br.com.fiap.global.controller.dto.ConsumoEnergeticoDTO;
import br.com.fiap.global.model.ConsumoEnergetico;
import br.com.fiap.global.service.ConsumoEnergeticoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ConsumoEnergeticoControllerTest {

    @Mock
    private ConsumoEnergeticoService consumoEnergeticoService;

    @Mock
    private Model model;

    @InjectMocks
    private ConsumoEnergeticoController consumoEnergeticoController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrarConsumoEnergeticoApi() {
        ConsumoEnergeticoDTO dto = new ConsumoEnergeticoDTO();
        ConsumoEnergetico consumo = new ConsumoEnergetico();

        when(consumoEnergeticoService.cadastrarConsumoEnergetico(any(ConsumoEnergeticoDTO.class))).thenReturn(consumo);

        ResponseEntity<ConsumoEnergetico> response = consumoEnergeticoController.cadastrarConsumoEnergeticoApi(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(consumo, response.getBody());
        verify(consumoEnergeticoService, times(1)).cadastrarConsumoEnergetico(dto);
    }

    @Test
    public void testAtualizarConsumoEnergeticoApi() {
        ConsumoEnergeticoDTO dto = new ConsumoEnergeticoDTO();
        ConsumoEnergetico consumo = new ConsumoEnergetico();

        when(consumoEnergeticoService.atualizarConsumoEnergetico(eq(1L), any(ConsumoEnergeticoDTO.class))).thenReturn(consumo);

        ResponseEntity<ConsumoEnergetico> response = consumoEnergeticoController.atualizarConsumoEnergeticoApi(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(consumo, response.getBody());
        verify(consumoEnergeticoService, times(1)).atualizarConsumoEnergetico(1L, dto);
    }

    @Test
    public void testListarConsumosEnergeticosApi() {
        List<ConsumoEnergetico> consumos = new ArrayList<>();
        when(consumoEnergeticoService.listarConsumosEnergeticos()).thenReturn(consumos);

        ResponseEntity<List<ConsumoEnergetico>> response = consumoEnergeticoController.listarConsumosEnergeticosApi();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(consumos, response.getBody());
        verify(consumoEnergeticoService, times(1)).listarConsumosEnergeticos();
    }

    @Test
    public void testRemoverConsumoEnergeticoApi() {
        ResponseEntity<Void> response = consumoEnergeticoController.removerConsumoEnergeticoApi(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(consumoEnergeticoService, times(1)).removerConsumoEnergetico(1L);
    }

    @Test
    public void testPaginaConsumosEnergeticos() {
        List<ConsumoEnergetico> consumos = new ArrayList<>();
        when(consumoEnergeticoService.listarConsumosEnergeticos()).thenReturn(consumos);

        String viewName = consumoEnergeticoController.paginaConsumosEnergeticos(model);

        assertEquals("consumo-energetico", viewName);
        verify(model, times(1)).addAttribute("consumosEnergeticos", consumos);
    }

    @Test
    public void testFormularioNovoConsumoEnergetico() {
        String viewName = consumoEnergeticoController.formularioNovoConsumoEnergetico(model);

        assertEquals("consumo-energetico-form", viewName);
        verify(model, times(1)).addAttribute(eq("consumoEnergetico"), any(ConsumoEnergeticoDTO.class));
    }

    @Test
    public void testFormularioEditarConsumoEnergetico() {
        ConsumoEnergeticoDTO dto = new ConsumoEnergeticoDTO();
        when(consumoEnergeticoService.buscarConsumoEnergeticoPorId(1L)).thenReturn(dto);

        String viewName = consumoEnergeticoController.formularioEditarConsumoEnergetico(1L, model);

        assertEquals("consumo-energetico-form", viewName);
        verify(model, times(1)).addAttribute("consumoEnergetico", dto);
    }
}

