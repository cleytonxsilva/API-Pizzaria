package main.pizzaria.dto;


import lombok.Getter;
import lombok.Setter;
import main.pizzaria.entity.Cliente;
import main.pizzaria.entity.Endereco;

@Getter
@Setter
public class EnderecoDTO {

    private Long id;

    private String rua;

    private Integer numero;

    private Cliente cliente;

    public Endereco transformObject() {
        return new Endereco(rua, numero, cliente);
    }
}
