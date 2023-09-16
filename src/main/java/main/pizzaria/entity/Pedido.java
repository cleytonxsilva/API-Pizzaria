package main.pizzaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.pizzaria.entity.enums.Estado;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pedidos", schema = "public")
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

    @ManyToMany(mappedBy = "pedidos")
    private List<Produto> produtos;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
}
