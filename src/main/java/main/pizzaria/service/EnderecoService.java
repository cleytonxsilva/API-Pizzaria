package main.pizzaria.service;

import main.pizzaria.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;
}
