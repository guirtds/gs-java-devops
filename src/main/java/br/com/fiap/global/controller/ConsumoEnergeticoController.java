package br.com.fiap.global.controller;

import br.com.fiap.global.controller.dto.ConsumoEnergeticoDTO;
import br.com.fiap.global.model.ConsumoEnergetico;
import br.com.fiap.global.service.ConsumoEnergeticoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/consumo-energetico")
public class ConsumoEnergeticoController {

    private final ConsumoEnergeticoService consumoEnergeticoService;

    @Autowired
    public ConsumoEnergeticoController(ConsumoEnergeticoService consumoEnergeticoService) {
        this.consumoEnergeticoService = consumoEnergeticoService;
    }

    // Endpoints da API REST
    @PostMapping
    @ResponseBody
    public ResponseEntity<ConsumoEnergetico> cadastrarConsumoEnergeticoApi(@Valid @RequestBody ConsumoEnergeticoDTO consumoEnergeticoDTO) {
        ConsumoEnergetico novoConsumoEnergetico = consumoEnergeticoService.cadastrarConsumoEnergetico(consumoEnergeticoDTO);
        return ResponseEntity.ok(novoConsumoEnergetico);
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseBody
    public ResponseEntity<ConsumoEnergetico> atualizarConsumoEnergeticoApi(@PathVariable Long id, @RequestBody ConsumoEnergeticoDTO novoConsumoEnergeticoDTO) {
        ConsumoEnergetico consumoEnergeticoAtualizado = consumoEnergeticoService.atualizarConsumoEnergetico(id, novoConsumoEnergeticoDTO);
        return ResponseEntity.ok(consumoEnergeticoAtualizado);
    }

    @GetMapping("/buscar")
    @ResponseBody
    public ResponseEntity<List<ConsumoEnergetico>> listarConsumosEnergeticosApi() {
        List<ConsumoEnergetico> consumosEnergeticos = consumoEnergeticoService.listarConsumosEnergeticos();
        return ResponseEntity.ok(consumosEnergeticos);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> removerConsumoEnergeticoApi(@PathVariable Long id) {
        consumoEnergeticoService.removerConsumoEnergetico(id);
        return ResponseEntity.ok().build();
    }

    // Métodos para renderização de páginas HTML via Thymeleaf
    @GetMapping("/consumos")
    public String paginaConsumosEnergeticos(Model model) {
        List<ConsumoEnergetico> consumosEnergeticos = consumoEnergeticoService.listarConsumosEnergeticos();
        model.addAttribute("consumosEnergeticos", consumosEnergeticos);
        return "consumo-energetico";  // Nome do arquivo HTML sem extensão
    }

    @GetMapping("/form")
    public String formularioNovoConsumoEnergetico(Model model) {
        model.addAttribute("consumoEnergetico", new ConsumoEnergeticoDTO());
        return "consumo-energetico-form";
    }

    @GetMapping("/form/{id}")
    public String formularioEditarConsumoEnergetico(@PathVariable Long id, Model model) {
        ConsumoEnergeticoDTO consumoEnergeticoDTO = consumoEnergeticoService.buscarConsumoEnergeticoPorId(id);
        model.addAttribute("consumoEnergetico", consumoEnergeticoDTO);
        return "consumo-energetico-form";
    }

    @PostMapping("/create")
    public String criarConsumoEnergetico(@ModelAttribute("consumoEnergetico") @Valid ConsumoEnergeticoDTO consumoEnergeticoDTO, Model model) {
        consumoEnergeticoService.cadastrarConsumoEnergetico(consumoEnergeticoDTO);
        return "redirect:/api/consumo-energetico/consumos"; // Redireciona para a página de listagem de consumos
    }

    @PostMapping("/update/{id}")
    public String atualizarConsumoEnergetico(@PathVariable Long id, @ModelAttribute("consumoEnergetico") @Valid ConsumoEnergeticoDTO consumoEnergeticoDTO) {
        consumoEnergeticoService.atualizarConsumoEnergetico(id, consumoEnergeticoDTO);
        return "redirect:/api/consumo-energetico/consumos"; // Redireciona para a página de listagem de consumos
    }

    @GetMapping("/detail/{id}")
    public String detalhesConsumoEnergetico(@PathVariable Long id, Model model) {
        ConsumoEnergeticoDTO consumoEnergeticoDTO = consumoEnergeticoService.buscarConsumoEnergeticoPorId(id);
        model.addAttribute("consumoEnergetico", consumoEnergeticoDTO);
        return "consumo-energetico-detail";  // Nome do arquivo HTML sem extensão
    }
}
