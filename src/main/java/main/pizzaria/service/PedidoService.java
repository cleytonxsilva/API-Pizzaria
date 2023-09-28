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

    @Transactional
    public void create(PedidoDTO pedidoDTO) {

    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Transactional
    public void update(Long id, PedidoDTO pedidoDTO) {

    }

    @Transactional
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }
}

