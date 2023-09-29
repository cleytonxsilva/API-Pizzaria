package main.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;
import main.pizzaria.entity.Cliente;
import main.pizzaria.entity.Pedido;
import main.pizzaria.entity.enums.Estado;
import main.pizzaria.entity.Funcionario;
import main.pizzaria.entity.Produto;

import java.math.BigDecimal;
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

    private BigDecimal valorTotal;

    private String descricao;

    private List<Funcionario> funcionarios;

    public Pedido transformObject() {
        return new Pedido(numeroPedido, entregar, cliente, produtos, estado, valorTotal, descricao, funcionarios);
    }
}
