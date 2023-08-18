package main.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clientes", schema = "private")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "CPF não pode ser nulo!")
    private String cpf;

    @NotNull(message = "Nome não pode ser nulo!")
    private String nome;

    private int idade;

    @NotNull(message = "Telefone não pode ser nulo!")
    private int telefone;

    @ManyToMany
    @JoinTable(
            name = "clientes_enderecos",
            joinColumns = @JoinColumn(name = "clientes_id"),
            inverseJoinColumns = @JoinColumn(name = "enderecos_id")
    )
    @NotNull(message = "Endereço não pode ser nulo!")
    private List<Endereco> enderecos = new ArrayList<>();


}
