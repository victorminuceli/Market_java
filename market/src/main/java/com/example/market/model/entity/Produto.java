package com.example.market.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(
            value = "0.01",
            message = "Preço deve ser maior que zero"
    )
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @NotBlank(message = "Categoria é obrigatória")
    @Column(nullable = false)
    private String categoria;

    @Column(name = "imagem_url", length = 500)
    private String imagemUrl;
}