package main.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;
import main.pizzaria.entity.Pedido;
import main.pizzaria.entity.Sabor;
import main.pizzaria.entity.Tamanho;

import java.util.List;

@Getter
@Setter
public class ProdutoDTO {

    private Long id;

    private String nome;

    private Tamanho tamanho;

    private double valorProduto;

    private List<Sabor> sabores;

    private List<Pedido> pedidos;
}
