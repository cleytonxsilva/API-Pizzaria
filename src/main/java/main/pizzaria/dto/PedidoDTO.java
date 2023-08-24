package main.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;
import main.pizzaria.entity.Cliente;
import main.pizzaria.entity.Estado;
import main.pizzaria.entity.Produto;

import java.util.List;
@Getter
@Setter
public class PedidoDTO {

    private Long id;

    private int numeroPedido;

    private boolean entregar;

    private Cliente cliente;

    private List<Produto> produtos;

    private Estado estado;

    private double valorTotal;

    private String descricao;
}
