package br.com.fiap.global.controller;

import br.com.fiap.global.controller.dto.PosteDTO;
import br.com.fiap.global.model.Poste;
import br.com.fiap.global.service.PosteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/poste")
public class PosteController {

    private final PosteService posteService;

    @Autowired
    public PosteController(PosteService posteService) {
        this.posteService = posteService;
    }

    // Endpoints da API REST
    @PostMapping
    @ResponseBody
    public ResponseEntity<Poste> cadastrarPosteApi(@Valid @RequestBody PosteDTO posteDTO) {
        Poste novoPoste = posteService.cadastrarPoste(posteDTO);
        return ResponseEntity.ok(novoPoste);
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseBody
    public ResponseEntity<Poste> atualizarPosteApi(@PathVariable Long id, @RequestBody PosteDTO novoPosteDTO) {
        Poste posteAtualizado = posteService.atualizarPoste(id, novoPosteDTO);
        return ResponseEntity.ok(posteAtualizado);
    }

    @GetMapping("/buscar")
    @ResponseBody
    public ResponseEntity<List<Poste>> listarPostesApi() {
        List<Poste> postes = posteService.listarPostes();
        return ResponseEntity.ok(postes);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> removerPosteApi(@PathVariable Long id) {
        posteService.removerPoste(id);
        return ResponseEntity.ok().build();
    }

    // Métodos para renderização de páginas HTML via Thymeleaf
    @GetMapping("/postes")
    public String paginaPostes(Model model) {
        List<Poste> postes = posteService.listarPostes();
        model.addAttribute("postes", postes);
        return "postes";  // Nome do arquivo HTML sem extensão
    }

    @GetMapping("/form")
    public String formularioNovoPoste(Model model) {
        model.addAttribute("poste", new PosteDTO());
        return "poste-form";
    }

    @GetMapping("/form/{id}")
    public String formularioEditarPoste(@PathVariable Long id, Model model) {
        PosteDTO posteDTO = posteService.buscarPostePorId(id);
        model.addAttribute("poste", posteDTO);
        return "poste-form";
    }

    @PostMapping("/create")
    public String criarPoste(@ModelAttribute("poste") @Valid PosteDTO posteDTO, Model model) {
        posteService.cadastrarPoste(posteDTO);
        return "redirect:/api/poste/postes"; // Redireciona para a página de listagem de postes
    }

    @PostMapping("/update/{id}")
    public String atualizarPoste(@PathVariable Long id, @ModelAttribute("poste") @Valid PosteDTO posteDTO) {
        posteService.atualizarPoste(id, posteDTO);
        return "redirect:/api/poste/postes"; // Redireciona para a página de listagem de postes
    }

    @GetMapping("/detail/{id}")
    public String detalhesPoste(@PathVariable Long id, Model model) {
        PosteDTO posteDTO = posteService.buscarPostePorId(id);
        model.addAttribute("poste", posteDTO);
        return "poste-detail";  // Nome do arquivo HTML sem extensão
    }
}
