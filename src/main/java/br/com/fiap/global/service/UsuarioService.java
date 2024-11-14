package br.com.fiap.global.service;

import br.com.fiap.global.controller.dto.UsuarioDTO;
import br.com.fiap.global.model.Usuario;
import br.com.fiap.global.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Cadastrando os usuarios
    public Usuario cadastrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        return usuarioRepository.save(usuario);
    }

    // Atualizando os usuarios
    public Usuario atualizarUsuario(Long id, UsuarioDTO novoUsuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);

        if (usuarioExistente.isPresent()) {
            Usuario usuarioAtualizado = usuarioExistente.get();
            usuarioAtualizado.setNome(novoUsuario.getNome());
            usuarioAtualizado.setEmail(novoUsuario.getEmail());
            usuarioAtualizado.setSenha(novoUsuario.getSenha());
            return usuarioRepository.save(usuarioAtualizado);
        } else {
            throw new RuntimeException("Usuário não encontrado com o o ID: " + id);
        }
    }

    // Listando os usuarios
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscando os usuarios pelo ID
    public UsuarioDTO buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuário não encontrado com o ID: " + id));
        return new UsuarioDTO(usuario);
    }

    // Deletando o usuario
    public void removerUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }
    }
}
