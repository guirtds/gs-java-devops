package br.com.fiap.global.service;

import br.com.fiap.global.controller.dto.EconomiaEnergeticaDTO;
import br.com.fiap.global.model.EconomiaEnergetica;
import br.com.fiap.global.repository.EconomiaEnergeticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EconomiaEnergeticaService {

    @Autowired
    private EconomiaEnergeticaRepository economiaEnergeticaRepository;

    // Cadastrando as economias energeticas
    public EconomiaEnergetica cadastrarEconomiaEnergetica(EconomiaEnergeticaDTO economiaEnergeticaDTO) {
        EconomiaEnergetica economiaEnergetica = new EconomiaEnergetica();
        economiaEnergetica.setPeriodo(economiaEnergeticaDTO.getPeriodo());
        economiaEnergetica.setEconomiaTotal(economiaEnergeticaDTO.getEconomiaTotal());
        return economiaEnergeticaRepository.save(economiaEnergetica);
    }

    // Atualizando os economias energeticas
    public EconomiaEnergetica atualizarEconomiaEnergetica(Long id, EconomiaEnergeticaDTO novaEconomiaEnergetica) {
        Optional<EconomiaEnergetica> economiaEnergeticaExistente = economiaEnergeticaRepository.findById(id);

        if (economiaEnergeticaExistente.isPresent()) {
            EconomiaEnergetica economiaEnergeticaAtualizada = economiaEnergeticaExistente.get();
            economiaEnergeticaAtualizada.setPeriodo(novaEconomiaEnergetica.getPeriodo());
            economiaEnergeticaAtualizada.setEconomiaTotal(novaEconomiaEnergetica.getEconomiaTotal());
            return economiaEnergeticaRepository.save(economiaEnergeticaAtualizada);
        } else {
            throw new RuntimeException("Economia energética não encontrada com o o ID: " + id);
        }
    }

    // Listando as economias energeticas
    public List<EconomiaEnergetica> listarEconomiasEnergeticas() {
        return economiaEnergeticaRepository.findAll();
    }

    // Buscando as economias energeticas pelo ID
    public EconomiaEnergeticaDTO buscarEconomiaEnergeticaPorId(Long id) {
        EconomiaEnergetica economiaEnergetica = economiaEnergeticaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Economia energética não encontrada com o ID: " + id));
        return new EconomiaEnergeticaDTO(economiaEnergetica);
    }

    // Deletando as economias energeticas
    public void removerEconomiaEnergetica(Long id) {
        if (economiaEnergeticaRepository.existsById(id)) {
            economiaEnergeticaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Economia energética não encontrada com o ID: " + id);
        }
    }
}
