package main.pizzaria.service;

import main.pizzaria.dto.ProdutoDTO;
import main.pizzaria.entity.Produto;
import main.pizzaria.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    @Transactional
    public void create(ProdutoDTO produtoDTO) {
        Assert.notNull(produtoDTO.getNome(), "Nome do produto não pode ser nulo");
        Assert.notNull(produtoDTO.getTamanho(), "Tamanho do produto não pode ser nulo");
        Assert.notNull(produtoDTO.getValorProduto(), "Valor do produto não pode ser nulo");

        Produto novoProduto = produtoDTO.transformObject();

        produtoRepository.save(novoProduto);
    }

    @Transactional
    public void update(Long id, ProdutoDTO produtoDTO) {
        Produto produtoExistente = produtoRepository.findById(id).orElse(null);

        if (produtoExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }

        Assert.notNull(produtoDTO.getNome(), "Nome do produto não pode ser nulo");
        Assert.notNull(produtoDTO.getTamanho(), "Tamanho do produto não pode ser nulo");
        Assert.notNull(produtoDTO.getValorProduto(), "Valor do produto não pode ser nulo");

        produtoExistente.setNome(produtoDTO.getNome());
        produtoExistente.setTamanho(produtoDTO.getTamanho());
        produtoExistente.setValorProduto(produtoDTO.getValorProduto());

        produtoRepository.save(produtoExistente);
    }

    @Transactional
    public void delete(Long id) {
        produtoRepository.deleteById(id);
    }
}
