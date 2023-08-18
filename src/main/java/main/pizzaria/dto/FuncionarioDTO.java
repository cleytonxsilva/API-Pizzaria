package main.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDTO {

    private Long id;

    private String nome;

    private String cpf;

    private int telefone;

    private int matricula;
}
