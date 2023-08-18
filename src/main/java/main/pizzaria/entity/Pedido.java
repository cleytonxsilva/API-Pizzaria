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
@Table(name = "pedidos", schema = "private")
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Número do pedido não pode ser nulo!")
    private int numeroPedido;

    @NotNull(message = "Campo 'entrega' não pode ser nulo!")
    private boolean entregar;

    @ManyToOne
    @JoinColumn(name = "clientes_id")
    @NotNull(message = "Cliente não pode ser nulo!")
    private Cliente cliente;

    @OneToMany
    @JoinTable(name = "produtos_id")
    @NotNull(message = "Produto não pode ser nulo!")
    private List<Produto> produtos;

    @NotNull(message = "Status não pode ser nulo!")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @NotNull(message = "Total não pode ser nulo!")
    private double total;

    @NotNull(message = "Descrição do produto obrigatório!")
    private String descricao;
}
