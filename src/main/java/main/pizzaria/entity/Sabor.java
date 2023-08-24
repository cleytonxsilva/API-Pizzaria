package main.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sabores", schema = "private")
@AllArgsConstructor
@NoArgsConstructor
public class Sabor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinTable(
            name = "sabores_produtos",
            joinColumns = @JoinColumn(name = "sabores_id"),
            inverseJoinColumns = @JoinColumn(name = "produtos_id")
    )
    private Produto produtos;

    @NotNull(message = "Valor não pode ser nulo!")
    private double valorSabor;

    private String descricao;
}
