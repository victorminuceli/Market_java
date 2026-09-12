package com.example.market.service;

import com.example.market.model.entity.Carrinho;
import com.example.market.model.entity.Usuario;
import com.example.market.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(Usuario usuario) {

        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(usuario);
        usuario.setCarrinho(carrinho);

        return usuarioRepository.save(usuario);
    }

    public Usuario login(String email, String senha) {

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));

        if (!usuario.getSenha().equals(senha)) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        return usuario;
    }

    public Usuario buscarPorId(Long id) {

        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario atualizar(Long id, Usuario dados) {

        Usuario usuario = buscarPorId(id);

        if (!usuario.getEmail().equals(dados.getEmail())
                && usuarioRepository.findByEmail(dados.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        usuario.setNome(dados.getNome());
        usuario.setEmail(dados.getEmail());
        usuario.setSenha(dados.getSenha());

        return usuarioRepository.save(usuario);
    }

    public void excluir(Long id) {

        Usuario usuario = buscarPorId(id);

        usuarioRepository.delete(usuario);
    }
}