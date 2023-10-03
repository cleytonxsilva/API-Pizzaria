package main.pizzaria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "clientes", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "idade")
    private int idade;

    @Column(name = "telefone", nullable = false, length = 14)
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public Cliente(String cpf, String nome, int idade, String telefone, List<Pedido> pedidos, Endereco endereco) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.pedidos = pedidos;
        this.endereco = endereco;
    }

}
