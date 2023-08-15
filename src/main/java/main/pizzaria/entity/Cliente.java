package main.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clientes", schema = "private")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String cpf;

    @NotNull
    private String nome;

    private int idade;

    @NotNull
    private int telefone;

    @NotNull
    @OneToMany
    private List<Endereco> enderecos;

}
