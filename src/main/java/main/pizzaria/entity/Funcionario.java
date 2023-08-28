package main.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "funcionarios", schema = "private")
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Nome do funcionário não pode ser nulo!")
    private String nome;

    @NotNull(message = "CPF não pode ser nulo!")
    private String cpf;

    private int telefone;

    @Column(unique = true)
    @NotNull(message = "Matricula não pode ser nula!") //unique
    private int matricula;

    @ManyToMany(mappedBy = "funcionarios")
    private List<Pedido> pedidos;

}
