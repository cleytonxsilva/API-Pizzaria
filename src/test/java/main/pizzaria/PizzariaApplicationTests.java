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
import java.util.Optional;

@SpringBootTest
class PizzariaApplicationTests {

	@MockBean
	ClienteRepository clienteRepository;

	@Autowired
	private ClienteController clienteController;

	@BeforeEach
	void injectData() {
		String nome = "Cleyton"; // Nome do cliente que você deseja simular
		Optional<Cliente> cliente = Optional.of(new Cliente("12345678900", nome, 26, "123456789", new ArrayList<>(1), new ArrayList<>(1)));
		Mockito.when(clienteRepository.findByNome(nome)).thenReturn(cliente);
	}


	@Test
	@DisplayName("Caso de Uso FindByName Classe Cliente")
	void testFindByNamePessoaTest(){
		ResponseEntity<Optional<Cliente>> responseEntity = clienteController.findByNome("Cleyton");
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		String nome = responseEntity.getBody().orElseThrow().getNome();
		System.out.println(nome);
		Assertions.assertEquals("Cleyton", nome);
	}

}
