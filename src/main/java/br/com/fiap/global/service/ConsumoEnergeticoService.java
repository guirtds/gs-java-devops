package br.com.fiap.global.service;

import br.com.fiap.global.controller.dto.ConsumoEnergeticoDTO;
import br.com.fiap.global.model.ConsumoEnergetico;
import br.com.fiap.global.repository.ConsumoEnergeticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumoEnergeticoService {

    @Autowired
    private ConsumoEnergeticoRepository consumoEnergeticoRepository;

    // Cadastrando os consumos energeticos
    public ConsumoEnergetico cadastrarConsumoEnergetico(ConsumoEnergeticoDTO consumoEnergeticoDTO) {
        ConsumoEnergetico consumoEnergetico = new ConsumoEnergetico();
        consumoEnergetico.setDataHora(consumoEnergeticoDTO.getDataHora());
        consumoEnergetico.setConsumo(consumoEnergeticoDTO.getConsumo());
        return consumoEnergeticoRepository.save(consumoEnergetico);
    }

    // Atualizando os consumos energeticos
    public ConsumoEnergetico atualizarConsumoEnergetico(Long id, ConsumoEnergeticoDTO novoConsumoEnergetico) {
        Optional<ConsumoEnergetico> consumoEnergeticoExistente = consumoEnergeticoRepository.findById(id);

        if (consumoEnergeticoExistente.isPresent()) {
            ConsumoEnergetico consumoEnergeticoAtualizado = consumoEnergeticoExistente.get();
            consumoEnergeticoAtualizado.setDataHora(novoConsumoEnergetico.getDataHora());
            consumoEnergeticoAtualizado.setConsumo(novoConsumoEnergetico.getConsumo());
            return consumoEnergeticoRepository.save(consumoEnergeticoAtualizado);
        } else {
            throw new RuntimeException("Consumo energético não encontrado com o o ID: " + id);
        }
    }

    // Listando os consumos energeticos
    public List<ConsumoEnergetico> listarConsumosEnergeticos() {
        return consumoEnergeticoRepository.findAll();
    }

    // Buscando os consumos energeticos pelo ID
    public ConsumoEnergeticoDTO buscarConsumoEnergeticoPorId(Long id) {
        ConsumoEnergetico consumoEnergetico = consumoEnergeticoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Consumo energético não encontrado com o ID: " + id));
        return new ConsumoEnergeticoDTO(consumoEnergetico);
    }

    // Deletando os consumos energeticos
    public void removerConsumoEnergetico(Long id) {
        if (consumoEnergeticoRepository.existsById(id)) {
            consumoEnergeticoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Consumo energético não encontrado com o ID: " + id);
        }
    }
}
