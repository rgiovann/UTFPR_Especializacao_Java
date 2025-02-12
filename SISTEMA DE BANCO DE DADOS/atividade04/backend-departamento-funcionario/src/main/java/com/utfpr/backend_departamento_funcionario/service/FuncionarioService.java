/**
 * Serviço responsável pelas operações de negócio relacionadas a Funcionários.
 *
 * Esta classe implementa a camada de serviço para Funcionários, fazendo a
 * intermediação entre o controlador e o repositório. Utiliza injeção de
 * dependência via construtor para maior testabilidade e segurança.
 *
 * Funcionalidades disponíveis:
 * - Listagem de todos os funcionários
 * - Busca por nome e quantidade de dependentes
 * - Listagem de funcionários por departamento
 * - Busca do funcionário com maior salário
 * - Listagem dos três funcionários com maiores salários
 * - Listagem de funcionários sem dependentes
 * - Busca de funcionários com salário maior que um valor (JPQL ou SQL nativo)
 * - Busca por quantidade de dependentes (Named Query)
 * - Busca por nome parcial (Named Native Query)
 *
 * @author Giovanni Leopoldo Rozza
 * @version 1.0
 * @date 12/02/2025
 *
 * @see Service
 * @see FuncionarioRepository
 * @see Funcionario
 * @see BigDecimal
 */

package com.utfpr.backend_departamento_funcionario.service;

import com.utfpr.backend_departamento_funcionario.entity.Funcionario;
import com.utfpr.backend_departamento_funcionario.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    // Constructor injection instead of @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioRepository.findAll();

    }

    // Metodo que encapsula a consulta do repositório
    public List<Funcionario> buscarFuncionarioPorNomeEDependentes(String nomeFuncionario, Integer qtdDependentes) {
        return funcionarioRepository.findByNomeFuncionarioAndQtdDependentes(nomeFuncionario, qtdDependentes);
    }

    public List<Funcionario> listaTodosFuncionariosPorDepartamento(Long departamentoId) {
        return funcionarioRepository.listaTodosFuncionariosPorDepartamento(departamentoId);
    }

    public Funcionario buscaUmFuncionarioMaiorSalario() {
        return funcionarioRepository.buscaUmFuncionarioMaiorSalario();
    }

    public List<Funcionario> listaTresFuncionariosMaiorSalario() {
        return funcionarioRepository.listaTresFuncionariosMaiorSalario();
    }

    public List<Funcionario> listaTodosFuncionariosSemDependentes() {
        return funcionarioRepository.listaTodosFuncionariosSemDependentes();
    }

    /**
     * Lista funcionários com salário maior que o valor especificado.
     *
     * @param salario valor do salário para comparação
     * @param tipoQuery tipo de query a ser utilizada (0 para JPQL, 1 para SQL nativo)
     * @return Lista de funcionários com salário maior que o especificado
     */
    public List<Funcionario> listaFuncionariosSalarioMaiorQue(BigDecimal salario, int tipoQuery) {
        // 0 = JPQL, 1 = Native
        return (tipoQuery == 0)
                ? funcionarioRepository.findBySalarioGreaterThan(salario)
                : funcionarioRepository.listaFuncionariosSalarioMaiorQueNative(salario);
    }

    public List<Funcionario> findByQtdDependendentes(Integer idade) {
        return funcionarioRepository.findByQtdDependendentes(idade);
    }

    public List<Funcionario> findByNomeParcial(String nome) {
        return funcionarioRepository.findByNomeParcial(nome);
    }
}
