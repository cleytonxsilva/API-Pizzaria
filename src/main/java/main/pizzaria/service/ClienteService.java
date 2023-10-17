package main.pizzaria.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import main.pizzaria.dto.ClienteDTO;
import main.pizzaria.entity.Cliente;
import main.pizzaria.repository.ClienteRepository;
import org.asynchttpclient.util.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
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
    public void create(ClienteDTO clienteDTO) {
        Assertions.assertNotNull(clienteDTO.getCpf(), "CPF não pode ser nulo");
        Assertions.assertNotNull(clienteDTO.getNome(), "Nome não pode ser nulo");
        Assertions.assertNotNull(clienteDTO.getTelefone(), "Telefone não pode ser nulo");
        Assertions.assertNotNull(clienteDTO.getEndereco(), "Endereço não pode ser nulo");

        Cliente novoCliente = new Cliente();
        novoCliente.setCpf(clienteDTO.getCpf());
        novoCliente.setNome(clienteDTO.getNome());
        novoCliente.setIdade(clienteDTO.getIdade());
        novoCliente.setTelefone(clienteDTO.getTelefone());
        novoCliente.setEndereco(clienteDTO.getEndereco());
        clienteRepository.save(novoCliente);
    }


    public Optional<Cliente> findByNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    @Transactional
    public void update(Long id, Cliente clienteDTO) {
        Assert.notNull(clienteDTO.getCpf(), "CPF não pode ser nulo");
        Assert.notNull(clienteDTO.getNome(), "Nome não pode ser nulo");
        Assert.notNull(clienteDTO.getTelefone(), "Telefone não pode ser nulo");

        Cliente clienteBanco = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado pelo ID: " + id));

        clienteBanco.setCpf(clienteDTO.getCpf());
        clienteBanco.setNome(clienteDTO.getNome());
        clienteBanco.setIdade(clienteDTO.getIdade());
        clienteBanco.setTelefone(clienteDTO.getTelefone());

//        List<Endereco> enderecos = new ArrayList<>();
//
//        for (Endereco endereco : clienteDTO.getEnderecos()) {
//            Assert.notNull(endereco, "Endereço não pode ser nulo");
//            Assert.notNull(endereco.getRua(), "Rua do endereço não pode ser nula");
//            Assert.notNull(endereco.getNumero(), "Número do endereço não pode ser nulo");
//
//            Endereco novoEndereco = new Endereco();
//            novoEndereco.setRua(endereco.getRua());
//            novoEndereco.setNumero(endereco.getNumero());
//            enderecos.add(novoEndereco);
//        }
//
//        Assert.isTrue(!enderecos.isEmpty(), "O cliente deve ter pelo menos um endereço.");
//
//        clienteBanco.setEnderecos(enderecos);

        clienteRepository.save(clienteBanco);
    }


    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
