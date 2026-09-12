package com.example.market.service;

import com.example.market.model.entity.Produto;
import com.example.market.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {

        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Produto atualizar(Long id, Produto dados) {

        Produto produto = buscarPorId(id);

        produto.setNome(dados.getNome());
        produto.setPreco(dados.getPreco());
        produto.setCategoria(dados.getCategoria());

        return produtoRepository.save(produto);
    }

    public void excluir(Long id) {

        Produto produto = buscarPorId(id);

        produtoRepository.delete(produto);
    }
}