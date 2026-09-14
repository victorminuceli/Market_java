package com.example.market.controller;

import com.example.market.dto.AtualizacaoUsuario;
import com.example.market.model.entity.Usuario;
import com.example.market.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(
                usuarioService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(
            @PathVariable("id") Long id) {

        return ResponseEntity.ok(
                usuarioService.buscarPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(
            @Valid @RequestBody Usuario usuario) {

        return ResponseEntity.ok(
                usuarioService.cadastrar(usuario)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(
            @RequestBody Usuario usuario) {

        return ResponseEntity.ok(
                usuarioService.login(
                        usuario.getEmail(),
                        usuario.getSenha()
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(
            @PathVariable("id") Long id,
            @Valid @RequestBody AtualizacaoUsuario dados) {

        return ResponseEntity.ok(
                usuarioService.atualizar(id, dados)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable("id") Long id) {

        usuarioService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}