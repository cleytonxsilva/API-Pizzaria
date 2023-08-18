package main.pizzaria.controller;

import main.pizzaria.dto.ClienteDTO;
import main.pizzaria.entity.Cliente;
import main.pizzaria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public ResponseEntity <List<Cliente>> findAll(){
        try{
            return ResponseEntity.ok(clienteService.findAll());
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao consultar a lista de clientes!", e);
        }
    }

    @GetMapping
    public ResponseEntity<Cliente> findById(@RequestParam("id") Long id) {
        Cliente cliente = clienteService.findById(id).orElse(null);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<?> create(@RequestBody final ClienteDTO cliente) {
        try {
            this.clienteService.create(cliente);
            return ResponseEntity.ok("Cliente cadastrado com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao cadastrar cliente!", e);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<?> update(@RequestParam("nome") final String nome, @RequestBody final Cliente cliente) {
        try{
            final Cliente clienteBanco = this.clienteService.findByNome(cliente.getNome()).orElse(null);
            if(clienteBanco == null)
            {
                throw new RuntimeException("Não foi possível identificar o registro informado");
            }
            this.clienteService.update(nome, clienteBanco);//parametros errados?
            return ResponseEntity.ok("Cliente editado com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao cadastrar cliente!", e);
        }
    }
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") final Long id){
        try {
            final Cliente clienteBanco = this.clienteService.findById(id).orElse(null);
            if(clienteBanco == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
            }
            clienteService.delete(id);
            return ResponseEntity.ok("Registro excluido com sucesso");
        }
        catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao excluir cliente!", e);
        }
    }
}
