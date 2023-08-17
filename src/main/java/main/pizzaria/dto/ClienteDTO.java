package main.pizzaria.dto;

import main.pizzaria.entity.Endereco;
import java.util.List;

public class ClienteDTO {
    private Long id;

    private String cpf;

    private String nome;

    private int idade;

    private int telefone;

    private List<Endereco> enderecos;

}
