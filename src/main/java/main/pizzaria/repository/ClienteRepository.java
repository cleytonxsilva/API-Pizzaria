package main.pizzaria.repository;

import main.pizzaria.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
     public Optional<Cliente> findByNome(String nome);
}