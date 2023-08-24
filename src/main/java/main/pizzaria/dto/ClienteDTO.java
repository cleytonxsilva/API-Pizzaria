package main.pizzaria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.pizzaria.entity.Endereco;
import main.pizzaria.entity.Pedido;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {
    private Long id;

    private String cpf;

    private String nome;

    private int idade;

    private int telefone;

    private List<Pedido> pedidos;

    private List<Endereco> enderecos;

}
