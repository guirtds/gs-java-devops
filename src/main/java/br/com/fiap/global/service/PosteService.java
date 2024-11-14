package br.com.fiap.global.service;

import br.com.fiap.global.controller.dto.PosteDTO;
import br.com.fiap.global.model.Poste;
import br.com.fiap.global.repository.PosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PosteService {

    @Autowired
    private PosteRepository posteRepository;

    // Cadastrando os postes
    public Poste cadastrarPoste(PosteDTO posteDTO) {
        Poste poste = new Poste();
        poste.setLocalizacao(posteDTO.getLocalizacao());
        poste.setPotencia(posteDTO.getPotencia());
        poste.setStatus(posteDTO.getStatus());
        poste.setConsumoAtual(posteDTO.getConsumoAtual());
        poste.setUltimoConsumo(posteDTO.getUltimoConsumo());
        return posteRepository.save(poste);
    }

    // Atualizando os postes
    public Poste atualizarPoste(Long id, PosteDTO novoPoste) {
        Optional<Poste> posteExistente = posteRepository.findById(id);

        if (posteExistente.isPresent()) {
            Poste posteAtualizado = posteExistente.get();
            posteAtualizado.setLocalizacao(novoPoste.getLocalizacao());
            posteAtualizado.setPotencia(novoPoste.getPotencia());
            posteAtualizado.setStatus(novoPoste.getStatus());
            posteAtualizado.setConsumoAtual(novoPoste.getConsumoAtual());
            posteAtualizado.setUltimoConsumo(novoPoste.getUltimoConsumo());
            return posteRepository.save(posteAtualizado);
        } else {
            throw new RuntimeException("Poste não localizado com o o ID: " + id);
        }
    }

    // Listando os postes
    public List<Poste> listarPostes() {
        return posteRepository.findAll();
    }

    // Buscando os postes pelo ID
    public PosteDTO buscarPostePorId(Long id) {
        Poste poste = posteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Poste não localizado com o ID: " + id));
        return new PosteDTO(poste);
    }

    // Deletando o poste
    public void removerPoste(Long id) {
        if (posteRepository.existsById(id)) {
            posteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Poste não localizado com o ID: " + id);
        }
    }
}
