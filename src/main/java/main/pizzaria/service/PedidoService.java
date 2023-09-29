package main.pizzaria.service;

import jakarta.transaction.Transactional;
import main.pizzaria.dto.PedidoDTO;
import main.pizzaria.entity.Pedido;
import main.pizzaria.repository.PedidoRepository;
import org.asynchttpclient.util.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        Assertions.assertNotNull(pedidoDTO.getNumeroPedido(), "Número do pedido não pode ser nulo");
        Assertions.assertNotNull(pedidoDTO.isEntregar(), "O campo 'entregar' não pode ser nulo");
        Assertions.assertNotNull(pedidoDTO.getCliente(), "Cliente não pode ser nulo");
        Assertions.assertNotNull(pedidoDTO.getProdutos(), "Produtos não podem ser nulos");
        Assertions.assertNotNull(pedidoDTO.getEstado(), "Estado não pode ser nulo");
        Assertions.assertNotNull(pedidoDTO.getValorTotal(), "Valor total não pode ser nulo");
        Assertions.assertNotNull(pedidoDTO.getDescricao(), "Descrição não pode ser nulo");
        Assertions.assertNotNull(pedidoDTO.getFuncionarios(), "Funcionario não pode ser nulo");

        Pedido novoPedido = pedidoDTO.transformObject();

        pedidoRepository.save(novoPedido);
    }



    @Transactional
    public void update(Long id, PedidoDTO pedidoDTO) {
        Pedido pedidoExistente = pedidoRepository.findById(id).orElse(null);

        if (pedidoExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }

        Assertions.assertNotNull(pedidoDTO.getNumeroPedido(), "Número do pedido não pode ser nulo");
        Assertions.assertNotNull(pedidoDTO.isEntregar(), "O campo 'entregar' não pode ser nulo");
        Assertions.assertNotNull(pedidoDTO.getCliente(), "Cliente não pode ser nulo");
        Assertions.assertNotNull(pedidoDTO.getProdutos(), "Produtos não podem ser nulos");
        Assertions.assertNotNull(pedidoDTO.getEstado(), "Estado não pode ser nulo");
        Assertions.assertNotNull(pedidoDTO.getValorTotal(), "Valor total não pode ser nulo");

        pedidoExistente.setNumeroPedido(pedidoDTO.getNumeroPedido());
        pedidoExistente.setEntregar(pedidoDTO.isEntregar());
        pedidoExistente.setCliente(pedidoDTO.getCliente());
        pedidoExistente.setProdutos(pedidoDTO.getProdutos());
        pedidoExistente.setEstado(pedidoDTO.getEstado());
        pedidoExistente.setValorTotal(pedidoDTO.getValorTotal());
        pedidoExistente.setDescricao(pedidoDTO.getDescricao());
        pedidoExistente.setFuncionarios(pedidoDTO.getFuncionarios());

        pedidoRepository.save(pedidoExistente);
    }


    @Transactional
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }
}

