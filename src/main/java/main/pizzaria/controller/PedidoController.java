package main.pizzaria.controller;

import main.pizzaria.dto.PedidoDTO;
import main.pizzaria.entity.Pedido;
import main.pizzaria.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Pedido>> findAll() {
        try {
            return ResponseEntity.ok(pedidoService.findAll());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao consultar a lista de pedidos!", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable final Long id) {
        try {
            return ResponseEntity.ok(pedidoService.findById(id).orElse(null));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não encontrado!", e);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> create(@RequestBody final PedidoDTO pedidoDTO) {
        try {
            pedidoService.create(pedidoDTO);
            return ResponseEntity.ok("Pedido cadastrado com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao cadastrar pedido!", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable final Long id, @RequestBody final PedidoDTO pedidoDTO) {
        try {
            pedidoService.update(id, pedidoDTO);
            return ResponseEntity.ok("Pedido editado com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao editar pedido!", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable final Long id) {
        try {
            pedidoService.delete(id);
            return ResponseEntity.ok("Pedido excluído com sucesso");
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao excluir pedido!", e);
        }
    }
}


