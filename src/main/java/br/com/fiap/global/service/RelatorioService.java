package br.com.fiap.global.service;

import br.com.fiap.global.controller.dto.RelatorioDTO;
import br.com.fiap.global.model.Relatorio;
import br.com.fiap.global.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;

    // Cadastrando os relatorios
    public Relatorio cadastrarRelatorio(RelatorioDTO relatorioDTO) {
        Relatorio relatorio = new Relatorio();
        relatorio.setTitulo(relatorioDTO.getTitulo());
        relatorio.setDataGeracao(relatorioDTO.getDataGeracao());
        relatorio.setConteudo(relatorio.getConteudo());
        return relatorioRepository.save(relatorio);
    }

    // Atualizando os reltorios
    public Relatorio atualizarRelatorio(Long id, RelatorioDTO novoRelatorio) {
        Optional<Relatorio> relatorioExistente = relatorioRepository.findById(id);

        if (relatorioExistente.isPresent()) {
            Relatorio relatorioAtualizado = relatorioExistente.get();
            relatorioAtualizado.setTitulo(novoRelatorio.getTitulo());
            relatorioAtualizado.setDataGeracao(novoRelatorio.getDataGeracao());
            relatorioAtualizado.setConteudo(novoRelatorio.getConteudo());
            return relatorioRepository.save(relatorioAtualizado);
        } else {
            throw new RuntimeException("Relatório não encontrado com o o ID: " + id);
        }
    }

    // Listando os relatorios
    public List<Relatorio> listarRelatorios() {
        return relatorioRepository.findAll();
    }

    // Buscando os relatorios pelo ID
    public RelatorioDTO buscarRelatorioPorId(Long id) {
        Relatorio relatorio = relatorioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Relatório não encontrado com o ID: " + id));
        return new RelatorioDTO(relatorio);
    }

    // Deletando o relatorio
    public void removerRelatorio(Long id) {
        if (relatorioRepository.existsById(id)) {
            relatorioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Relatório não encontrado com o ID: " + id);
        }
    }
}
