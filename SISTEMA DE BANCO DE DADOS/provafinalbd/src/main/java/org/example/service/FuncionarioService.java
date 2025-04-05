package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.entity.Cargo;
import org.example.entity.Funcionario;
import org.example.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoService cargoService;


    public Optional<Funcionario> buscaFuncionario(Long id) {
        return funcionarioRepository.findById(id);
    }

    public List<Funcionario> listarFuncionarios() {

        return funcionarioRepository.findAll();

    }

    public List<Funcionario> listaFuncionarioOrdenadoNomeCrescente(){
        return funcionarioRepository.listaNomeFuncOrdemAlfabCresc();
    }

    public Long totalFuncionarios(){
        return funcionarioRepository.totalFuncionarios();
    }


    /**
     * Salva um funcionário no repositório após validar e processar seu cargo.
     * <p>
     * Este método verifica se o funcionário possui um cargo associado e realiza o processamento
     * adequado do cargo antes de salvar o funcionário. O cargo pode ser novo (sem ID) ou
     * existente (com ID).
     *
     * @param funcionario O objeto Funcionario a ser salvo no repositório
     * @return O objeto Funcionario salvo com as atualizações do banco de dados
     * @throws IllegalArgumentException se o cargo do funcionário for nulo ou se o cargo com
     *                                  o ID informado não existir no banco de dados
     */
    public Funcionario salvarFuncionario(Funcionario funcionario) {

        if (funcionario.getCargo() == null) {
            throw new IllegalArgumentException("O cargo do funcionário NÃO pode ser nulo.");
        }

        Cargo cargo = funcionario.getCargo();

        if (cargo.getId() == null) {
            // Se o Cargo não tem ID, salvar antes pois é cargo NOVO
            cargo = cargoService.salvarCargo(cargo);
        } else {
            // Buscar Cargo no banco e lançar exceção se não existir
            cargo = cargoService.buscaCargo(cargo.getId())
                    .orElseThrow(() -> new EntityNotFoundException("O cargo informado NÃO existe no banco de dados."));
        }

        funcionario.setCargo(cargo);
        return funcionarioRepository.save(funcionario);
    }

    public void deletarFuncionario(Long id) {
        Funcionario cargo = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado com o ID: " + id));
        funcionarioRepository.deleteById(id);
    }
}
