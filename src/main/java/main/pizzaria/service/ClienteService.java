package main.pizzaria.service;

import jakarta.transaction.Transactional;
import main.pizzaria.entity.Cliente;
import main.pizzaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Transactional
    public void create(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Optional<Cliente> findByNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    @Transactional
    public void update(String nome, Cliente cliente) {
        Cliente clienteBanco = findByNome(nome).orElseThrow(() ->
                new RuntimeException("Cliente não encontrado pelo nome: " + nome)
        );
        clienteBanco.setNome(cliente.getNome());
        clienteBanco.setTelefone(cliente.getTelefone());
        clienteBanco.setEnderecos(cliente.getEnderecos());

        clienteRepository.save(clienteBanco);
    }
}
