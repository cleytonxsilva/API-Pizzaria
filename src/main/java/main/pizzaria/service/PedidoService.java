package main.pizzaria.service;

import main.pizzaria.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;
}
