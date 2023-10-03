package main.pizzaria.controller;


import main.pizzaria.dto.ProdutoDTO;
import main.pizzaria.entity.Produto;
import main.pizzaria.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {


    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> findAll() {
        try {
            return ResponseEntity.ok(produtoService.findAll());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao consultar a lista de produtos!", e);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Produto> findById(@RequestParam("id") final Long id) {
        try {
            return ResponseEntity.ok(produtoService.findById(id).orElse(null));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado!", e);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> create(@RequestBody final ProdutoDTO produtoDTO) {
        try {
            produtoService.create(produtoDTO);
            return ResponseEntity.ok("Produto cadastrado com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao cadastrar produto!", e);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<String> update(@RequestParam("id") final Long id, @RequestBody final ProdutoDTO produtoDTO) {
        try {
            produtoService.update(id, produtoDTO);
            return ResponseEntity.ok("Produto editado com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao editar produto!", e);
        }
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<String> delete(@RequestParam("id") final Long id) {
        try {
            produtoService.delete(id);
            return ResponseEntity.ok("Produto excluído com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao excluir produto!", e);
        }
    }
}
