package main.pizzaria.service;

import main.pizzaria.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaborService {

    @Autowired
    SaborRepository saborRepository;
}
