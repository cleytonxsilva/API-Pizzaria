package main.pizzaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.pizzaria.entity.enums.Tamanho;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "produtos", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Enumerated(EnumType.STRING)
    private Tamanho tamanho;

    @Column(name = "valor_produto", nullable = false)
    private BigDecimal valorProduto;

    @OneToMany(mappedBy = "produto")
    private List<Sabor> sabores;

    @ManyToMany
    @JoinTable(name = "pedidos_produtos",
            joinColumns = @JoinColumn(name = "produtos_id"),
            inverseJoinColumns = @JoinColumn(name = "pedidos_id"))
    private List<Pedido> pedidos;

    public Produto(String nome, Tamanho tamanho, BigDecimal valorProduto, List<Sabor> sabores, List<Pedido> pedidos) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.valorProduto = valorProduto;
        this.sabores = sabores;
        this.pedidos = pedidos;
    }
}
