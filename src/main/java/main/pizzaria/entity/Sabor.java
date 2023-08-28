package main.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @ManyToOne
    @JoinTable(
            name = "sabores_produtos",
            joinColumns = @JoinColumn(name = "sabores_id"),
            inverseJoinColumns = @JoinColumn(name = "produtos_id")
    )
    @NotNull(message = "Produto não pode ser nulo!")
    private Produto produtos;

    @Column(name = "valor_sabor", nullable = false)
    private BigDecimal valorSabor;

    @Column(name = "descricao", length = 100)
    private String descricao;
}
