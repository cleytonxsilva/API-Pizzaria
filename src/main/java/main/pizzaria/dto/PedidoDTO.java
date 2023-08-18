package main.pizzaria.dto;

import main.pizzaria.entity.Cliente;
import main.pizzaria.entity.Estado;
import main.pizzaria.entity.Produto;

import java.util.List;

public class PedidoDTO {

    private Long id;

    private int numeroPedido;

    private boolean entregar;

    private Cliente cliente;

    private List<Produto> produtos;

    private Estado estado;

    private double total;

    private String descricao;
}
