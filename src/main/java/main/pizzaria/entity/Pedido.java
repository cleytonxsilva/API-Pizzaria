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
@Table(name = "pedidos", schema = "private")
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int numeroPedidos;

    @NotNull
    private boolean entregar;

    @ManyToOne
    @JoinColumn(name = "clientes_id")
    @NotNull
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "produtos_id")
    @NotNull
    private Produto produtos;
}
