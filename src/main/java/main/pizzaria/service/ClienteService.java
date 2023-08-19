package main.pizzaria.service;

import jakarta.transaction.Transactional;
import main.pizzaria.dto.ClienteDTO;
import main.pizzaria.entity.Cliente;
import main.pizzaria.entity.Endereco;
import main.pizzaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void create(ClienteDTO cliente) {
        Cliente novoCliente = new Cliente();
        novoCliente.setCpf(cliente.getCpf());
        novoCliente.setNome(cliente.getNome());
        novoCliente.setIdade(cliente.getIdade());
        novoCliente.setTelefone(cliente.getTelefone());

        List<Endereco> enderecos = new ArrayList<>();

        for(Endereco enderecoDTO : cliente.getEnderecos()){

            Endereco novoEndereco = new Endereco();

            novoEndereco.setRua(enderecoDTO.getRua());
            novoEndereco.setNumero(enderecoDTO.getNumero());
            enderecos.add(novoEndereco);
        }
        novoCliente.setEnderecos(enderecos);
        clienteRepository.save(novoCliente);
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

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
