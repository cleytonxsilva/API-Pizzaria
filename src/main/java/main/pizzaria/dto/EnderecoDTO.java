package main.pizzaria.dto;


import lombok.Getter;
import lombok.Setter;
import main.pizzaria.entity.Cliente;

@Getter
@Setter
public class EnderecoDTO {

    private Long id;

    private String rua;

    private Integer numero;

    private Cliente cliente;

}
