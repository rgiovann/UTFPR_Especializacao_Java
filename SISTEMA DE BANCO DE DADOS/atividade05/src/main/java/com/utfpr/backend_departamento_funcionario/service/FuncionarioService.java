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
 * - Aumento salarial baseado em percentual
 *  Listagem de funcionários sem dependentes por departamento
 * - Transferência de funcionários entre departamentos
 * - Exclusão de funcionários de um departamento
 *
 * @author Giovanni Leopoldo Rozza
 * @version 1.1
 * @since 25/02/2025
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

    //*********************************************************************
    // INICIO DOS MÉTODOS DA Atividade JPA - Manipular Dados e Transações
    //*********************************************************************

    // item 01
    public void aumentarSalario(BigDecimal percentual) {
        funcionarioRepository.aumentarSalario(percentual);
    }

    // item 02
    public List<Funcionario> listaTodosFuncionariosSemDependentesPorDepartamento(Long departamentoId) {
        return funcionarioRepository.listaFuncionariosSemDependentesPorDepartamento(departamentoId);
    }

    // item 03
    public int transferirFuncionariosDeDepartamento(Long departamentoIdOrigem, Long departamentoIdDestino) {
        return funcionarioRepository.transferirFuncionariosDeDepartamento(departamentoIdOrigem, departamentoIdDestino);
    }

    // item 04
    public int deletaFuncionariosDepartamento(Long departamentoId) {
        return funcionarioRepository.deletaFuncionariosDepartamento(departamentoId);
    }
}
