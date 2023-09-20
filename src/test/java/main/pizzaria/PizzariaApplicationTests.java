package main.pizzaria;

import main.pizzaria.controller.ClienteController;
import main.pizzaria.entity.Cliente;
import main.pizzaria.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class PizzariaApplicationTests {

	@MockBean
	ClienteRepository clienteRepository;

	@Autowired
	private ClienteController clienteController;

	@BeforeEach
	void injectData() {
		// Simule o comportamento do clienteRepository.findAll() aqui
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(new Cliente("12345678900", "Cleyton", 26, "123456789", new ArrayList<>(1), new ArrayList<>(1)));
		clientes.add(new Cliente("98765432100", "João", 30, "987654321", new ArrayList<>(1), new ArrayList<>(1)));
		Mockito.when(clienteRepository.findAll()).thenReturn(clientes);
	}


	@Test
	@DisplayName("Caso de Uso FindByName Classe Cliente")
	void testFindByNameClienteTest(){
		ResponseEntity<Optional<Cliente>> responseEntity = clienteController.findByNome("Cleyton");
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		String nome = responseEntity.getBody().orElseThrow().getNome();
		System.out.println(nome);
		Assertions.assertEquals("Cleyton", nome);
	}

	@Test
	@DisplayName("Caso de Uso FindAll Classe Cliente")
	void testFindAllClientesTest() {
		ResponseEntity<List<Cliente>> responseEntity = clienteController.findAll();
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		List<Cliente> clientes = responseEntity.getBody();
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
}
