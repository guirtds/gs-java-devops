package br.com.fiap.global.controller;

import br.com.fiap.global.controller.dto.RelatorioDTO;
import br.com.fiap.global.model.Relatorio;
import br.com.fiap.global.service.RelatorioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/relatorio")
public class RelatorioController {

    private final RelatorioService relatorioService;

    @Autowired
    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    // Endpoints da API REST
    @PostMapping
    @ResponseBody
    public ResponseEntity<Relatorio> cadastrarRelatorioApi(@Valid @RequestBody RelatorioDTO relatorioDTO) {
        Relatorio novoRelatorio = relatorioService.cadastrarRelatorio(relatorioDTO);
        return ResponseEntity.ok(novoRelatorio);
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseBody
    public ResponseEntity<Relatorio> atualizarRelatorioApi(@PathVariable Long id, @RequestBody RelatorioDTO novoRelatorioDTO) {
        Relatorio relatorioAtualizado = relatorioService.atualizarRelatorio(id, novoRelatorioDTO);
        return ResponseEntity.ok(relatorioAtualizado);
    }

    @GetMapping("/buscar")
    @ResponseBody
    public ResponseEntity<List<Relatorio>> listarRelatoriosApi() {
        List<Relatorio> relatorios = relatorioService.listarRelatorios();
        return ResponseEntity.ok(relatorios);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> removerRelatorioApi(@PathVariable Long id) {
        relatorioService.removerRelatorio(id);
        return ResponseEntity.ok().build();
    }

    // Métodos para renderização de páginas HTML via Thymeleaf
    @GetMapping("/relatorios")
    public String paginaRelatorios(Model model) {
        List<Relatorio> relatorios = relatorioService.listarRelatorios();
        model.addAttribute("relatorios", relatorios);
        return "relatorios";  // Nome do arquivo HTML sem extensão
    }

    @GetMapping("/form")
    public String formularioNovoRelatorio(Model model) {
        model.addAttribute("relatorio", new RelatorioDTO());
        return "relatorio-form";
    }

    @GetMapping("/form/{id}")
    public String formularioEditarRelatorio(@PathVariable Long id, Model model) {
        RelatorioDTO relatorioDTO = relatorioService.buscarRelatorioPorId(id);
        model.addAttribute("relatorio", relatorioDTO);
        return "relatorio-form";
    }

    @PostMapping("/create")
    public String criarRelatorio(@ModelAttribute("relatorio") @Valid RelatorioDTO relatorioDTO, Model model) {
        relatorioService.cadastrarRelatorio(relatorioDTO);
        return "redirect:/api/relatorio/relatorios"; // Redireciona para a página de listagem de relatórios
    }

    @PostMapping("/update/{id}")
    public String atualizarRelatorio(@PathVariable Long id, @ModelAttribute("relatorio") @Valid RelatorioDTO relatorioDTO) {
        relatorioService.atualizarRelatorio(id, relatorioDTO);
        return "redirect:/api/relatorio/relatorios"; // Redireciona para a página de listagem de relatórios
    }

    @GetMapping("/detail/{id}")
    public String detalhesRelatorio(@PathVariable Long id, Model model) {
        RelatorioDTO relatorioDTO = relatorioService.buscarRelatorioPorId(id);
        model.addAttribute("relatorio", relatorioDTO);
        return "relatorio-detail";  // Nome do arquivo HTML sem extensão
    }
}
