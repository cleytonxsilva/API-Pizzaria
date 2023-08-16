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

    @NotNull
    private int numeroPedidos;

    @NotNull
    private boolean entregar;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "clientes_id")
    private Cliente cliente;

    @NotNull
    @OneToMany
    @JoinColumn(name = "produtos_id")
    private List<Produto> produtos;
}
