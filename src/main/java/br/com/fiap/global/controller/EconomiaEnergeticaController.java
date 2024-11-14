package br.com.fiap.global.controller;

import br.com.fiap.global.controller.dto.EconomiaEnergeticaDTO;
import br.com.fiap.global.model.EconomiaEnergetica;
import br.com.fiap.global.service.EconomiaEnergeticaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/economia-energetica")
public class EconomiaEnergeticaController {

    private final EconomiaEnergeticaService economiaEnergeticaService;

    @Autowired
    public EconomiaEnergeticaController(EconomiaEnergeticaService economiaEnergeticaService) {
        this.economiaEnergeticaService = economiaEnergeticaService;
    }

    // Endpoints da API REST
    @PostMapping
    @ResponseBody
    public ResponseEntity<EconomiaEnergetica> cadastrarEconomiaEnergeticaApi(@Valid @RequestBody EconomiaEnergeticaDTO economiaEnergeticaDTO) {
        EconomiaEnergetica novaEconomiaEnergetica = economiaEnergeticaService.cadastrarEconomiaEnergetica(economiaEnergeticaDTO);
        return ResponseEntity.ok(novaEconomiaEnergetica);
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseBody
    public ResponseEntity<EconomiaEnergetica> atualizarEconomiaEnergeticaApi(@PathVariable Long id, @RequestBody EconomiaEnergeticaDTO novaEconomiaEnergeticaDTO) {
        EconomiaEnergetica economiaEnergeticaAtualizada = economiaEnergeticaService.atualizarEconomiaEnergetica(id, novaEconomiaEnergeticaDTO);
        return ResponseEntity.ok(economiaEnergeticaAtualizada);
    }

    @GetMapping("/buscar")
    @ResponseBody
    public ResponseEntity<List<EconomiaEnergetica>> listarEconomiasEnergeticasApi() {
        List<EconomiaEnergetica> economiasEnergeticas = economiaEnergeticaService.listarEconomiasEnergeticas();
        return ResponseEntity.ok(economiasEnergeticas);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> removerEconomiaEnergeticaApi(@PathVariable Long id) {
        economiaEnergeticaService.removerEconomiaEnergetica(id);
        return ResponseEntity.ok().build();
    }

    // Métodos para renderização de páginas HTML via Thymeleaf
    @GetMapping("/economias")
    public String paginaEconomiasEnergeticas(Model model) {
        List<EconomiaEnergetica> economiasEnergeticas = economiaEnergeticaService.listarEconomiasEnergeticas();
        model.addAttribute("economiasEnergeticas", economiasEnergeticas);
        return "economia-energetica";  // Nome do arquivo HTML sem extensão
    }

    @GetMapping("/form")
    public String formularioNovaEconomiaEnergetica(Model model) {
        model.addAttribute("economiaEnergetica", new EconomiaEnergeticaDTO());
        return "economia-energetica-form";
    }

    @GetMapping("/form/{id}")
    public String formularioEditarEconomiaEnergetica(@PathVariable Long id, Model model) {
        EconomiaEnergeticaDTO economiaEnergeticaDTO = economiaEnergeticaService.buscarEconomiaEnergeticaPorId(id);
        model.addAttribute("economiaEnergetica", economiaEnergeticaDTO);
        return "economia-energetica-form";
    }

    @PostMapping("/create")
    public String criarEconomiaEnergetica(@ModelAttribute("economiaEnergetica") @Valid EconomiaEnergeticaDTO economiaEnergeticaDTO, Model model) {
        economiaEnergeticaService.cadastrarEconomiaEnergetica(economiaEnergeticaDTO);
        return "redirect:/api/economia-energetica/economias"; // Redireciona para a página de listagem de economias
    }

    @PostMapping("/update/{id}")
    public String atualizarEconomiaEnergetica(@PathVariable Long id, @ModelAttribute("economiaEnergetica") @Valid EconomiaEnergeticaDTO economiaEnergeticaDTO) {
        economiaEnergeticaService.atualizarEconomiaEnergetica(id, economiaEnergeticaDTO);
        return "redirect:/api/economia-energetica/economias"; // Redireciona para a página de listagem de economias
    }

    @GetMapping("/detail/{id}")
    public String detalhesEconomiaEnergetica(@PathVariable Long id, Model model) {
        EconomiaEnergeticaDTO economiaEnergeticaDTO = economiaEnergeticaService.buscarEconomiaEnergeticaPorId(id);
        model.addAttribute("economiaEnergetica", economiaEnergeticaDTO);
        return "economia-energetica-detail";  // Nome do arquivo HTML sem extensão
    }
}
