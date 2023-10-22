package main.pizzaria.controller;

import main.pizzaria.dto.ClienteDTO;
import main.pizzaria.entity.Cliente;
import main.pizzaria.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/listar")
    public ResponseEntity <List<Cliente>> findAll(){
        try{
            return ResponseEntity.ok(clienteService.findAll());
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao consultar a lista de clientes!", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable final Long id){
        try {
            return ResponseEntity.ok(clienteService.findById(id).orElse(null));
        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado!", e);
        }

    }

    @GetMapping("/pesquisar")
    public ResponseEntity<Optional<Cliente>> findByNome(@RequestParam("nome") String nome){
        Optional<Cliente> cliente = clienteService.findByNome(nome);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> create(@RequestBody final ClienteDTO cliente) {
        try {
            this.clienteService.create(cliente);
            return ResponseEntity.ok("Cliente cadastrado com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao cadastrar cliente!", e);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<String> update(@RequestParam("nome") final Long id, @RequestBody final ClienteDTO clienteDTO) {
        try {

            Optional<Cliente> clienteBancoOptional = clienteService.findById(id);


            if (clienteBancoOptional.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
            }
            Cliente clienteBanco = clienteBancoOptional.get();

            clienteService.update(id, clienteBanco);
            return ResponseEntity.ok("Cliente editado com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar cliente", e);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final Long id){
        try {
            final Cliente clienteBanco = this.clienteService.findById(id).orElse(null);
            if(clienteBanco == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
            }
            clienteService.delete(id);
            return ResponseEntity.ok("Registro excluido com sucesso");
        }
        catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao excluir cadastro de cliente!", e);
        }
    }
}
