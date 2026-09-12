package com.example.market.service;

import com.example.market.model.entity.Carrinho;
import com.example.market.model.entity.ItemCarrinho;
import com.example.market.model.entity.ItemPedido;
import com.example.market.model.entity.Pedido;
import com.example.market.model.entity.StatusPedido;
import com.example.market.model.entity.Usuario;
import com.example.market.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioService usuarioService;
    private final CarrinhoService carrinhoService;

    public PedidoService(
            PedidoRepository pedidoRepository,
            UsuarioService usuarioService,
            CarrinhoService carrinhoService) {

        this.pedidoRepository = pedidoRepository;
        this.usuarioService = usuarioService;
        this.carrinhoService = carrinhoService;
    }

    @Transactional
    public Pedido finalizar(Long usuarioId) {

        Usuario usuario = usuarioService.buscarPorId(usuarioId);

        Carrinho carrinho = carrinhoService.buscarPorUsuario(usuarioId);

        if (carrinho.getItens().isEmpty()) {
            throw new RuntimeException("Carrinho está vazio");
        }

        Pedido pedido = new Pedido();

        pedido.setUsuario(usuario);
        pedido.setData(LocalDateTime.now());
        pedido.setStatus(StatusPedido.FINALIZADO);

        List<ItemPedido> itensPedido = new ArrayList<>();

        BigDecimal total = BigDecimal.ZERO;

        for (ItemCarrinho itemCarrinho : carrinho.getItens()) {

            ItemPedido itemPedido = new ItemPedido();

            itemPedido.setPedido(pedido);
            itemPedido.setProduto(itemCarrinho.getProduto());
            itemPedido.setQuantidade(itemCarrinho.getQuantidade());
            itemPedido.setPreco(itemCarrinho.getProduto().getPreco());

            BigDecimal subtotal = itemCarrinho.getProduto()
                    .getPreco()
                    .multiply(BigDecimal.valueOf(itemCarrinho.getQuantidade()));

            total = total.add(subtotal);

            itensPedido.add(itemPedido);
        }

        pedido.setItens(itensPedido);
        pedido.setValorTotal(total);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        carrinho.getItens().clear();

        return pedidoSalvo;
    }

    public List<Pedido> listarPorUsuario(Long usuarioId) {

        usuarioService.buscarPorId(usuarioId);

        return pedidoRepository.findByUsuarioIdOrderByDataDesc(usuarioId);
    }

    public Pedido buscarPorId(Long usuarioId, Long pedidoId) {

        usuarioService.buscarPorId(usuarioId);

        return pedidoRepository.findByIdAndUsuarioId(pedidoId, usuarioId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }
}