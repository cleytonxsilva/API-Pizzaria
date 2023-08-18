package main.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;
import main.pizzaria.entity.Tamanho;
@Getter
@Setter
public class ProdutoDTO {

    private Long id;

    private String nome;

    private Tamanho tamanho;

    private double valor;
}
