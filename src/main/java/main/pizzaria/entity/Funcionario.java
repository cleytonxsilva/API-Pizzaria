package main.pizzaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "funcionarios", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "telefone", nullable = false, length = 14)
    private Integer telefone;

    @Column(unique = true, nullable = false)
    private Integer matricula;

    public Funcionario(String nome, String cpf, Integer telefone, Integer matricula) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.matricula = matricula;
    }
}
