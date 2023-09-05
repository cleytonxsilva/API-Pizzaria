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
@Table(name = "sabores", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Sabor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "produtos_id", nullable = false)
    private Produto produtos;

    @Column(name = "valor_sabor", nullable = false)
    private BigDecimal valorSabor;

    @Column(name = "descricao", length = 100)
    private String descricao;
}
