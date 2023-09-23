package main.pizzaria.ClienteTest;

import main.pizzaria.dto.ClienteDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteDtoTest {

    @Test
    @DisplayName("Caso de uso Teste DTO")
    void ClienteDTOTest(){
        ClienteDTO cliente = new ClienteDTO();
        cliente.setNome("Cleyton");
        System.out.println(cliente);
        Assertions.assertEquals("Cleyton", cliente.getNome());
    }
}
