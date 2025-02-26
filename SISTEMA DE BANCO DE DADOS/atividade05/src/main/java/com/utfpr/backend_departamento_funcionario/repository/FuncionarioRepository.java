/**
 * Interface de repositório para a entidade Funcionário.
 *
 * Esta interface estende JpaRepository para fornecer operações básicas de CRUD
 * e outras funcionalidades de persistência para a entidade Funcionário.
 *
 * Implementa métodos personalizados de consulta usando diferentes abordagens:
 * consultas nativas SQL, JPQL e consultas nomeadas.
 *
 * Métodos de consulta disponíveis:
 * - findByNomeFuncionarioAndQtdDependentes: Busca por nome e quantidade de dependentes
 * - buscaUmFuncionarioMaiorSalario: Retorna o funcionário com maior salário
 * - listaTresFuncionariosMaiorSalario: Lista os 3 funcionários com maiores salários
 * - listaTodosFuncionariosPorDepartamento: Lista funcionários de um departamento específico
 * - listaTodosFuncionariosSemDependentes: Lista funcionários sem dependentes
 * - findBySalarioGreaterThan: Lista funcionários com salário maior (JPQL)
 * - listaFuncionariosSalarioMaiorQueNative: Lista funcionários com salário maior (SQL nativo)
 * - findByQtdDependendentes: Busca por quantidade de dependentes (Named Query)
 * - findByNomeParcial: Busca por parte do nome (Named Native Query)
 * - aumentarSalario: Procedimento armazenado para aumentar o salário
 * - listaFuncionariosSemDependentesPorDepartamento: Lista funcionários sem dependentes por departamento
 * - transferirFuncionariosDeDepartamento: Transfere funcionários entre departamentos
 * - deletaFuncionariosDepartamento: Remove todos os funcionários de um departamento
 *
 * @author Giovanni Leopoldo Rozza
 * @version 1.1
 * @since 25/02/2025
 */

package com.utfpr.backend_departamento_funcionario.repository;

import com.utfpr.backend_departamento_funcionario.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

// transações com readonly=true GLOBAL, annotation modificando para false no metodo quando necessário
@Transactional(readOnly = true)
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

    // item 01
    List<Funcionario> findByNomeFuncionarioAndQtdDependentes(String nomeFuncionario, Integer qtdDependentes);

    // item 02
    @Query(value="SELECT f FROM Funcionario f WHERE f.departamento.id = ?1")
    List<Funcionario> listaTodosFuncionariosPorDepartamento(Long departamentoId);

    // item 04
    @Query(value="SELECT * FROM funcionario AS f WHERE f.salario=(SELECT MAX(salario) FROM funcionario LIMIT 1)",nativeQuery = true)
    Funcionario buscaUmFuncionarioMaiorSalario();

    // item 05
    @Query(value="SELECT * FROM funcionario ORDER BY salario DESC LIMIT 3",nativeQuery = true)
    List<Funcionario> listaTresFuncionariosMaiorSalario();

    // item 06
    @Query(value="SELECT f FROM Funcionario f WHERE f.qtdDependentes = 0 ORDER BY f.nomeFuncionario ASC")
    List<Funcionario> listaTodosFuncionariosSemDependentes();

    // item 07
    @Query(value = "SELECT f FROM Funcionario f WHERE f.salario > ?1 ORDER BY f.nomeFuncionario ASC")
    List<Funcionario> findBySalarioGreaterThan(BigDecimal salario);

    // item 0
    @Query(value = "SELECT * FROM funcionario f WHERE f.salario > ?1 ORDER BY f.nome_funcionario ASC",nativeQuery = true)
    List<Funcionario> listaFuncionariosSalarioMaiorQueNative(BigDecimal salario);

    // item 11
    @Query(name="Funcionario.byQtdDependentes")
    List<Funcionario> findByQtdDependendentes(Integer qtdDependentes);

    @Query(name = "Funcionario.byNomeParcial")
    List<Funcionario> findByNomeParcial(String nome);

    //*********************************************************************
    // INICIO DAS QUERIES DA Atividade JPA - Manipular Dados e Transações
    //*********************************************************************

    // item 01
    @Procedure(value = "aumentar_salario")
    void aumentarSalario( @Param("percentual") BigDecimal percentual);

    // item 02
    @Query("SELECT f FROM Funcionario f WHERE f.departamento.id = :departamentoId AND f.qtdDependentes = 0")
    List<Funcionario> listaFuncionariosSemDependentesPorDepartamento(@Param("departamentoId") Long departamentoId);

    // item 03
    @Modifying
    @Transactional(readOnly = false)
    @Query("UPDATE Funcionario f SET f.departamento.id = :departamentoFinal WHERE f.departamento.id = :departamentoInicial")
    int transferirFuncionariosDeDepartamento(@Param("departamentoInicial") Long departamentoInicial,
                                             @Param("departamentoFinal") Long departamentoFinal);
    // item 04
    @Modifying
    @Transactional(readOnly = false)
    @Query("DELETE Funcionario f WHERE f.departamento.id = :departamentoId")
    int deletaFuncionariosDepartamento(@Param("departamentoId") Long departamentoId);

}
