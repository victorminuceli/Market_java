package com.example.market.controller;

import com.example.market.model.entity.Carrinho;
import com.example.market.service.CarrinhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Carrinho> buscarCarrinho(
            @PathVariable("usuarioId") Long usuarioId) {

        return ResponseEntity.ok(
                carrinhoService.buscarPorUsuario(usuarioId)
        );
    }

    @PostMapping("/{usuarioId}/itens/{produtoId}")
    public ResponseEntity<Carrinho> adicionarItem(
            @PathVariable("usuarioId") Long usuarioId,
            @PathVariable("produtoId") Long produtoId,
            @RequestParam("quantidade") Integer quantidade) {

        return ResponseEntity.ok(
                carrinhoService.adicionarItem(
                        usuarioId,
                        produtoId,
                        quantidade
                )
        );
    }

    @PutMapping("/{usuarioId}/itens/{itemId}")
    public ResponseEntity<Carrinho> atualizarItem(
            @PathVariable("usuarioId") Long usuarioId,
            @PathVariable("itemId") Long itemId,
            @RequestParam("quantidade") Integer quantidade) {

        return ResponseEntity.ok(
                carrinhoService.atualizarItem(
                        usuarioId,
                        itemId,
                        quantidade
                )
        );
    }

    @DeleteMapping("/{usuarioId}/itens/{itemId}")
    public ResponseEntity<Void> removerItem(
            @PathVariable("usuarioId") Long usuarioId,
            @PathVariable("itemId") Long itemId) {

        carrinhoService.removerItem(usuarioId, itemId);

        return ResponseEntity.noContent().build();
    }
}