package com.example.market.service;

import com.example.market.model.entity.Carrinho;
import com.example.market.model.entity.ItemCarrinho;
import com.example.market.model.entity.Produto;
import com.example.market.repository.CarrinhoRepository;
import com.example.market.repository.ItemCarrinhoRepository;
import com.example.market.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoService(
            CarrinhoRepository carrinhoRepository,
            ItemCarrinhoRepository itemCarrinhoRepository,
            ProdutoRepository produtoRepository) {

        this.carrinhoRepository = carrinhoRepository;
        this.itemCarrinhoRepository = itemCarrinhoRepository;
        this.produtoRepository = produtoRepository;
    }

    public Carrinho buscarPorUsuario(Long usuarioId) {

        return carrinhoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
    }

    public Carrinho adicionarItem(Long usuarioId, Long produtoId, Integer quantidade) {

        if (quantidade == null || quantidade < 1) {
            throw new RuntimeException("Quantidade deve ser maior que zero");
        }

        Carrinho carrinho = buscarPorUsuario(usuarioId);

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        ItemCarrinho item = itemCarrinhoRepository
                .findByCarrinhoIdAndProdutoId(carrinho.getId(), produtoId)
                .orElse(null);

        if (item != null) {

            item.setQuantidade(item.getQuantidade() + quantidade);

            itemCarrinhoRepository.save(item);

        } else {

            item = new ItemCarrinho();
            item.setCarrinho(carrinho);
            item.setProduto(produto);
            item.setQuantidade(quantidade);

            itemCarrinhoRepository.save(item);
            carrinho.getItens().add(item);
        }

        return carrinho;
    }

    public Carrinho atualizarItem(
            Long usuarioId,
            Long itemId,
            Integer quantidade) {

        if (quantidade == null || quantidade < 1) {
            throw new RuntimeException("Quantidade deve ser maior que zero");
        }

        Carrinho carrinho = buscarPorUsuario(usuarioId);

        ItemCarrinho item = itemCarrinhoRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item do carrinho não encontrado"));

        if (!item.getCarrinho().getId().equals(carrinho.getId())) {
            throw new RuntimeException("Item não pertence ao carrinho");
        }

        item.setQuantidade(quantidade);

        itemCarrinhoRepository.save(item);

        return carrinho;
    }

    public void removerItem(Long usuarioId, Long itemId) {

        Carrinho carrinho = buscarPorUsuario(usuarioId);

        ItemCarrinho item = itemCarrinhoRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item do carrinho não encontrado"));

        if (!item.getCarrinho().getId().equals(carrinho.getId())) {
            throw new RuntimeException("Item não pertence ao carrinho");
        }

        carrinho.getItens().remove(item);
        itemCarrinhoRepository.delete(item);
    }
}