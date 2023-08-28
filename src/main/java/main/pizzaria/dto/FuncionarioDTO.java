package main.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;
import main.pizzaria.entity.Pedido;

import java.util.List;

@Getter
@Setter
public class FuncionarioDTO {

    private Long id;

    private String nome;

    private String cpf;

    private Integer telefone;

    private int matricula;

    private List<Pedido> pedidos;
}
