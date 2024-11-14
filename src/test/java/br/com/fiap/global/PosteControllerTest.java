package br.com.fiap.global;

import br.com.fiap.global.controller.PosteController;
import br.com.fiap.global.controller.dto.PosteDTO;
import br.com.fiap.global.model.Poste;
import br.com.fiap.global.service.PosteService;
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

class PosteControllerTest {

    @Mock
    private PosteService posteService;

    @Mock
    private Model model;

    @InjectMocks
    private PosteController posteController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrarPosteApi() {
        PosteDTO dto = new PosteDTO();
        Poste poste = new Poste();

        when(posteService.cadastrarPoste(any(PosteDTO.class))).thenReturn(poste);

        ResponseEntity<Poste> response = posteController.cadastrarPosteApi(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(poste, response.getBody());
        verify(posteService, times(1)).cadastrarPoste(dto);
    }

    @Test
    public void testAtualizarPosteApi() {
        PosteDTO dto = new PosteDTO();
        Poste poste = new Poste();

        when(posteService.atualizarPoste(eq(1L), any(PosteDTO.class))).thenReturn(poste);

        ResponseEntity<Poste> response = posteController.atualizarPosteApi(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(poste, response.getBody());
        verify(posteService, times(1)).atualizarPoste(1L, dto);
    }

    @Test
    public void testListarPostesApi() {
        List<Poste> postes = new ArrayList<>();
        when(posteService.listarPostes()).thenReturn(postes);

        ResponseEntity<List<Poste>> response = posteController.listarPostesApi();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postes, response.getBody());
        verify(posteService, times(1)).listarPostes();
    }

    @Test
    public void testRemoverPosteApi() {
        ResponseEntity<Void> response = posteController.removerPosteApi(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(posteService, times(1)).removerPoste(1L);
    }

    @Test
    public void testPaginaPostes() {
        List<Poste> postes = new ArrayList<>();
        when(posteService.listarPostes()).thenReturn(postes);

        String viewName = posteController.paginaPostes(model);

        assertEquals("postes", viewName);
        verify(model, times(1)).addAttribute("postes", postes);
    }

    @Test
    public void testFormularioNovoPoste() {
        String viewName = posteController.formularioNovoPoste(model);

        assertEquals("poste-form", viewName);
        verify(model, times(1)).addAttribute(eq("poste"), any(PosteDTO.class));
    }

    @Test
    public void testFormularioEditarPoste() {
        PosteDTO dto = new PosteDTO();
        when(posteService.buscarPostePorId(1L)).thenReturn(dto);

        String viewName = posteController.formularioEditarPoste(1L, model);

        assertEquals("poste-form", viewName);
        verify(model, times(1)).addAttribute("poste", dto);
    }
}
