package br.com.fiap.global.controller;

import br.com.fiap.global.controller.dto.UsuarioDTO;
import br.com.fiap.global.model.Usuario;
import br.com.fiap.global.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Endpoints da API REST
    @PostMapping
    @ResponseBody
    public ResponseEntity<Usuario> cadastrarUsuarioApi(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario novoUsuario = usuarioService.cadastrarUsuario(usuarioDTO);
        return ResponseEntity.ok(novoUsuario);
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseBody
    public ResponseEntity<Usuario> atualizarUsuarioApi(@PathVariable Long id, @RequestBody UsuarioDTO novoUsuarioDTO) {
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, novoUsuarioDTO);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @GetMapping("/buscar")
    @ResponseBody
    public ResponseEntity<List<Usuario>> listarUsuariosApi() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> removerUsuarioApi(@PathVariable Long id) {
        usuarioService.removerUsuario(id);
        return ResponseEntity.ok().build();
    }

    // Métodos para renderização de páginas HTML via Thymeleaf
    @GetMapping("/usuarios")
    public String paginaUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";  // Nome do arquivo HTML sem extensão
    }

    @GetMapping("/form")
    public String formularioNovoUsuario(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "usuario-form";
    }

    @GetMapping("/form/{id}")
    public String formularioEditarUsuario(@PathVariable Long id, Model model) {
        UsuarioDTO usuarioDTO = usuarioService.buscarUsuarioPorId(id);
        model.addAttribute("usuario", usuarioDTO);
        return "usuario-form";
    }

    @PostMapping("/create")
    public String criarUsuario(@ModelAttribute("usuario") @Valid UsuarioDTO usuarioDTO, Model model) {
        usuarioService.cadastrarUsuario(usuarioDTO);
        return "redirect:/api/usuario/usuarios"; // Redireciona para a página de listagem de usuários
    }

    @PostMapping("/update/{id}")
    public String atualizarUsuario(@PathVariable Long id, @ModelAttribute("usuario") @Valid UsuarioDTO usuarioDTO) {
        usuarioService.atualizarUsuario(id, usuarioDTO);
        return "redirect:/api/usuario/usuarios"; // Redireciona para a página de listagem de usuários
    }

    @GetMapping("/detail/{id}")
    public String detalhesUsuario(@PathVariable Long id, Model model) {
        UsuarioDTO usuarioDTO = usuarioService.buscarUsuarioPorId(id);
        model.addAttribute("usuario", usuarioDTO);
        return "usuario-detail";  // Nome do arquivo HTML sem extensão
    }
}
