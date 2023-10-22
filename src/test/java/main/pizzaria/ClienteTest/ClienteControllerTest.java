package main.pizzaria.ClienteTest;

import main.pizzaria.controller.ClienteController;
import main.pizzaria.entity.Cliente;
import main.pizzaria.entity.Endereco;
import main.pizzaria.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@SpringBootTest
class ClienteControllerTest {
    @MockBean
    ClienteRepository clienteRepository;

    @Autowired
    private ClienteController clienteController;

    @BeforeEach
    void injectData() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("12345678900", "Cleyton", 26, "123456789", new Endereco()));
        clientes.add(new Cliente("98765432100", "João", 30, "987654321", new Endereco()));
        Mockito.when(clienteRepository.findAll()).thenReturn(clientes);

        String nomeProcurado = "Cleyton";
        Optional<Cliente> clienteCleyton = clientes.stream()
                .filter(cliente -> nomeProcurado.equals(cliente.getNome()))
                .findFirst();
        Mockito.when(clienteRepository.findByNome(nomeProcurado)).thenReturn(clienteCleyton);
    }

    @Test
    @DisplayName("Caso de Uso FindById")
    void findByIdTest() {
        Long clienteId = 1L;

        Cliente cliente = new Cliente("12345678900", "Cleyton", 26, "123456789", new Endereco());
        Mockito.when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));
        ResponseEntity<Cliente> responseEntity = clienteController.findById(clienteId);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals(cliente, responseEntity.getBody());
    }

    @Test
    @DisplayName("Caso de Uso FindByName Classe Cliente")
    void findByNomeClienteTest(){
        ResponseEntity<Optional<Cliente>> responseEntity = clienteController.findByNome("Cleyton");
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        String nome = responseEntity.getBody().orElseThrow().getNome();
        System.out.println(nome);
        Assertions.assertEquals("Cleyton", nome);
    }
    @Test
    @DisplayName("Caso de Uso FindByName Cliente não encontrado")
    void findByNomeNotFoundTest() {
        String nomeInexistente = "NomeInexistente";
        Mockito.when(clienteRepository.findByNome(nomeInexistente)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            clienteController.findByNome(nomeInexistente);
        });
    }

    @Test
    @DisplayName("Caso de Uso FindAll Classe Cliente")
    void findAllClientesTest() {
        ResponseEntity<List<Cliente>> responseEntity = clienteController.findAll();
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<Cliente> clientes = responseEntity.getBody();
        assert clientes != null;
        Assertions.assertFalse(clientes.isEmpty());

        Cliente primeiroCliente = clientes.get(0);
        Assertions.assertEquals("12345678900", primeiroCliente.getCpf());
        System.out.println(primeiroCliente.getCpf());
        Assertions.assertEquals("Cleyton", primeiroCliente.getNome());
        System.out.println(primeiroCliente.getNome());

        Cliente segundoCliente = clientes.get(1);
        Assertions.assertEquals("98765432100", segundoCliente.getCpf());
        System.out.println(segundoCliente.getCpf());
        Assertions.assertEquals("João", segundoCliente.getNome());
        System.out.println(segundoCliente.getNome());
    }

    @Test
    @DisplayName("Caso de Uso FindAll com Erro de Banco de Dados")
    void findAllWithDatabaseErrorTest() {
        Mockito.when(clienteRepository.findAll()).thenThrow(new DataIntegrityViolationException("Erro de banco de dados"));

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            clienteController.findAll();
        });
    }


}
