package main.pizzaria.controller;

import main.pizzaria.dto.SaborDTO;
import main.pizzaria.entity.Sabor;
import main.pizzaria.service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/sabores")
public class SaborController {

    @Autowired
    private SaborService saborService;

    @GetMapping("/listar")
    public ResponseEntity<List<Sabor>> findAll() {
        try {
            return ResponseEntity.ok(saborService.findAll());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao consultar a lista de sabores!", e);
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<Sabor> findById(@RequestParam("id") final Long id) {
        try {
            return ResponseEntity.ok(saborService.findById(id).orElse(null));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado!", e);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> create(@RequestBody final SaborDTO saborDTO) {
        try {
            saborService.create(saborDTO);
            return ResponseEntity.ok("Sabor cadastrado com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao cadastrar sabor!", e);
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<String> update(@RequestParam("id") final Long id, @RequestBody final SaborDTO saborDTO) {
        try {
            saborService.update(id, saborDTO);
            return ResponseEntity.ok("Sabor editado com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao editar sabor!", e);
        }
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<String> delete(@RequestParam("id") final Long id) {
        try {
            saborService.delete(id);
            return ResponseEntity.ok("Sabor excluído com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao excluir sabor!", e);
        }
    }
}
