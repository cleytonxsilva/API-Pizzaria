package main.pizzaria.controller;

import main.pizzaria.dto.EnderecoDTO;
import main.pizzaria.entity.Endereco;
import main.pizzaria.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Endereco>> findAll() {
        try {
            return ResponseEntity.ok(enderecoService.findAll());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao consultar a lista de endereços!", e);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Endereco> findById(@RequestParam("id") final Long id) {
        try {
            return ResponseEntity.ok(enderecoService.findById(id).orElse(null));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado!", e);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> create(@RequestBody final EnderecoDTO endereco) {
        try {
            enderecoService.create(endereco);
            return ResponseEntity.ok("Endereço cadastrado com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao cadastrar endereço!", e);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<String> update(@RequestParam("id") final Long id, @RequestBody final EnderecoDTO endereco) {
        try {
            final Endereco enderecoBanco = enderecoService.findById(id).orElse(null);
            if (enderecoBanco != null) {
                // Atualize os campos do endereço existente com os valores do novo endereço
                enderecoService.update(id, endereco);
                return ResponseEntity.ok("Endereço editado com sucesso");
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado");
            }
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao editar endereço!", e);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("id") final Long id) {
        try {
            final Endereco enderecoBanco = enderecoService.findById(id).orElse(null);
            if (enderecoBanco != null) {
                enderecoService.delete(id);
                return ResponseEntity.ok("Registro excluído com sucesso");
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado");
            }
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao excluir cadastro de endereço!", e);
        }
    }
}
