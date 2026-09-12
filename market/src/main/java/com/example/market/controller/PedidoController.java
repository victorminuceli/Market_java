package com.example.market.controller;

import com.example.market.model.entity.Pedido;
import com.example.market.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/{usuarioId}/finalizar")
    public ResponseEntity<Pedido> finalizar(
            @PathVariable("usuarioId") Long usuarioId) {

        return ResponseEntity.ok(
                pedidoService.finalizar(usuarioId)
        );
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Pedido>> listar(
            @PathVariable("usuarioId") Long usuarioId) {

        return ResponseEntity.ok(
                pedidoService.listarPorUsuario(usuarioId)
        );
    }

    @GetMapping("/{usuarioId}/{pedidoId}")
    public ResponseEntity<Pedido> buscarPorId(
            @PathVariable("usuarioId") Long usuarioId,
            @PathVariable("pedidoId") Long pedidoId) {

        return ResponseEntity.ok(
                pedidoService.buscarPorId(usuarioId, pedidoId)
        );
    }
}