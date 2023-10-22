package main.pizzaria.service;

import jakarta.transaction.Transactional;
import main.pizzaria.dto.FuncionarioDTO;
import main.pizzaria.entity.Funcionario;
import main.pizzaria.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> findById(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Optional<Funcionario> findByNome(String nome) {
        return funcionarioRepository.findByNome(nome);
    }

    @Transactional
    public void create(FuncionarioDTO funcionario) {
        Funcionario novoFuncionario = new FuncionarioDTO().transformObject();

        novoFuncionario.setNome(funcionario.getNome());
        novoFuncionario.setCpf(funcionario.getCpf());
        novoFuncionario.setTelefone(funcionario.getTelefone());
        novoFuncionario.setMatricula(funcionario.getMatricula());

        funcionarioRepository.save(novoFuncionario);
    }

    @Transactional
    public void update(Long id, Funcionario funcionario) {
        Funcionario funcionarioBanco = findById(id).orElse(null);
        assert funcionarioBanco != null;
        funcionarioBanco.setNome(funcionario.getNome());
        funcionarioBanco.setTelefone(funcionario.getTelefone());
        funcionarioBanco.setMatricula(funcionario.getMatricula());

        funcionarioRepository.save(funcionarioBanco);
    }

    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
