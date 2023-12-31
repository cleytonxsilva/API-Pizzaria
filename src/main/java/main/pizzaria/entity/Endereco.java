package main.pizzaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "enderecos", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rua", nullable = false, length = 50)
    private String rua;

    @Column(name = "numero", nullable = false)
    private Integer numero;

    public Endereco(String rua, Integer numero, Cliente cliente) {
        this.rua = rua;
        this.numero = numero;
    }
}
