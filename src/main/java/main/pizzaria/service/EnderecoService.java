package main.pizzaria.service;

import jakarta.transaction.Transactional;
import main.pizzaria.dto.EnderecoDTO;
import main.pizzaria.entity.Endereco;
import main.pizzaria.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public void create(EnderecoDTO enderecoDTO) {
        Endereco novoEndereco = enderecoDTO.transformObject();
        enderecoRepository.save(novoEndereco);
    }

    public Optional<Endereco> findById(Long id) {
        return enderecoRepository.findById(id);
    }

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    @Transactional
    public void update(Long id, EnderecoDTO enderecoDTO) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findById(id);

        if (enderecoExistente.isPresent()) {
            Endereco enderecoAtualizado = enderecoDTO.transformObject();
            enderecoAtualizado.setId(id);

            enderecoRepository.save(enderecoAtualizado);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado");
        }
    }

    public void delete(Long id) {
        enderecoRepository.deleteById(id);
    }
}

