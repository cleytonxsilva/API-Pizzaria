package main.pizzaria.entity;

import jakarta.persistence.*;
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
    @JoinColumn(name = "produtos_id")
    private Produto produto;

    @Column(name = "valor_sabor", nullable = false)
    private BigDecimal valorSabor;

    @Column(name = "descricao", length = 100)
    private String descricao;

    public Sabor(String nome, Produto produto, BigDecimal valorSabor, String descricao) {
        this.nome = nome;
        this.produto = produto;
        this.valorSabor = valorSabor;
        this.descricao = descricao;
    }
}
