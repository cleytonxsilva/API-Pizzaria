package main.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;
import main.pizzaria.entity.Produto;
import main.pizzaria.entity.Sabor;

import java.math.BigDecimal;
@Getter
@Setter
public class SaborDTO {

    private Long id;

    private String nome;

    private Produto produto;

    private BigDecimal valorSabor;

    private String descricao;

    public Sabor transformObject() {
        return new Sabor(nome, produto, valorSabor, descricao);
    }
}
