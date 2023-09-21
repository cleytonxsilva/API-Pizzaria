package main.pizzaria.repository;

import main.pizzaria.entity.Cliente;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
     Optional<Cliente> findByNome(String nome);

     @NotNull
     Optional<Cliente> findById(@NotNull Long id);

}