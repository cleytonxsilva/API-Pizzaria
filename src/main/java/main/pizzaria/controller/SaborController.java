package main.pizzaria.controller;

import main.pizzaria.service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sabores")
public class SaborController {

    @Autowired
    SaborService saborService;
}
