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
@Table(name = "produtos", schema = "private")
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome do produto não pode ser nulo!")
    private String nome;

    @NotNull(message = "Tamanho não pode ser nulo!")
    private String tamanho;

    @OneToMany
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Ingrediente não pode ser nulo!")
    private List<Ingrediente> ingredientes;
}
