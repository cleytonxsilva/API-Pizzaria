package main.pizzaria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.pizzaria.entity.Cliente;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

    private Long id;

    private String rua;

    private int numero;

    private Cliente cliente;

}
