package main.pizzaria.dto;


import lombok.Getter;
import lombok.Setter;
import main.pizzaria.entity.Cliente;
import main.pizzaria.entity.Endereco;
import main.pizzaria.entity.Pedido;
import java.util.List;

@Getter
@Setter
public class ClienteDTO {
    private Long id;

    private String cpf;

    private String nome;

    private int idade;

    private String telefone;

    private List<Pedido> pedidos;

    private Endereco endereco;


    public Cliente transformObject(){
        return new Cliente(cpf, nome, idade, telefone, pedidos, endereco);
    }
}
