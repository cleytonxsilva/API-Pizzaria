package main.pizzaria.service;

import jakarta.transaction.Transactional;
import main.pizzaria.dto.PedidoDTO;
import main.pizzaria.entity.Pedido;
import main.pizzaria.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Transactional
    public void create(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoDTO.transformObject();
        //Lógica de criação e valores
        pedidoRepository.save(pedido);
    }


    @Transactional
    public void update(Long id, PedidoDTO pedidoDTO) {
        Pedido pedidoExistente = pedidoRepository.findById(id).orElse(null);
        if (pedidoExistente == null) {
            throw new RuntimeException("Pedido não encontrado");
        }
        // Atualizar os campos do pedido existente com base nos dados da DTO
        pedidoRepository.save(pedidoExistente);
    }

    @Transactional
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }
}

