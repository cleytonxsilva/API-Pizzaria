package main.pizzaria.service;

import main.pizzaria.dto.SaborDTO;
import main.pizzaria.entity.Sabor;
import main.pizzaria.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class SaborService {

    @Autowired
    private SaborRepository saborRepository;

    public List<Sabor> findAll() {
        return saborRepository.findAll();
    }

    public Optional<Sabor> findById(Long id) {
        return saborRepository.findById(id);
    }

    @Transactional
    public void create(SaborDTO saborDTO) {
        Assert.notNull(saborDTO.getNome(), "Nome do sabor não pode ser nulo");
        Assert.notNull(saborDTO.getValorSabor(), "Valor do sabor não pode ser nulo");

        Sabor novoSabor = saborDTO.transformObject();

        saborRepository.save(novoSabor);
    }

    @Transactional
    public void update(Long id, SaborDTO saborDTO) {
        Sabor saborExistente = saborRepository.findById(id).orElse(null);

        if (saborExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sabor não encontrado");
        }

        Assert.notNull(saborDTO.getNome(), "Nome do sabor não pode ser nulo");
        Assert.notNull(saborDTO.getProduto(), "Produto do sabor não pode ser nulo");
        Assert.notNull(saborDTO.getValorSabor(), "Valor do sabor não pode ser nulo");

        saborExistente.setNome(saborDTO.getNome());
        saborExistente.setProduto(saborDTO.getProduto());
        saborExistente.setValorSabor(saborDTO.getValorSabor());
        saborExistente.setDescricao(saborDTO.getDescricao());

        saborRepository.save(saborExistente);
    }

    @Transactional
    public void delete(Long id) {
        saborRepository.deleteById(id);
    }
}
