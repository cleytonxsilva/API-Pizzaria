package main.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pedidos", schema = "private")
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_pedido", unique = true, nullable = false)
    private int numeroPedido;

    @Column(name = "entregar", nullable = false)
    private boolean entregar;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    //@NotNull(message = "Cliente não pode ser nulo!")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "pedidos_produtos",
            joinColumns = @JoinColumn(name = "pedidos_id"),
            inverseJoinColumns = @JoinColumn(name = "produtos_id"))
    //@NotNull(message = "Produto não pode ser nulo!")
    private List<Produto> produtos;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @ManyToMany(mappedBy = "pedidos")
    private List<Funcionario> funcionarios;
}
