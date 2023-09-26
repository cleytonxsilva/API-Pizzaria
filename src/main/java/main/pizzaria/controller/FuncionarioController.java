package main.pizzaria.controller;

import main.pizzaria.dto.FuncionarioDTO;
import main.pizzaria.entity.Funcionario;
import main.pizzaria.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Funcionario>> findAll() {
        try {
            return ResponseEntity.ok(funcionarioService.findAll());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao consultar a lista de funcionários!", e);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Funcionario> findById(@RequestParam("id") final Long id) {
        try {
            return ResponseEntity.ok(funcionarioService.findById(id).orElse(null));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado!", e);
        }
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<Optional<Funcionario>> findByNome(@RequestParam("nome") String nome){
        Optional<Funcionario> funcionario = funcionarioService.findByNome(nome);
        if (funcionario.isPresent()) {
            return ResponseEntity.ok(funcionario);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario não encontrado");
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> create(@RequestBody final FuncionarioDTO funcionario) {
        try {
            this.funcionarioService.create(funcionario);
            return ResponseEntity.ok("Funcionario cadastrado com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Erro ao cadastrar funcionario!", e);
        }
    }
}
