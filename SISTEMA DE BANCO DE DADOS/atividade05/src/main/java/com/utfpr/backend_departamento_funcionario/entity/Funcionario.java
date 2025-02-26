/**
 * Entidade que representa um Funcionário no sistema.
 *
 * Esta classe é mapeada para a tabela "funcionario" no banco de dados e mantém
 * o registro de funcionários, incluindo suas informações pessoais e profissionais,
 * bem como seu vínculo com um departamento.
 *
 * A classe possui duas queries nomeadas:
 * - "Funcionario.byQtdDependentes": Busca funcionários por quantidade de dependentes
 * - "Funcionario.byNomeParcial": Busca funcionários por parte do nome (case insensitive)
 *
 * Atributos:
 * - id: Código único do funcionário (auto-incremento)
 * - nomeFuncionario: Nome completo do funcionário (máximo 100 caracteres)
 * - departamento: Departamento ao qual o funcionário está vinculado
 * - qtdDependentes: Número de dependentes do funcionário
 * - salario: Salário do funcionário (precisão de 7 dígitos com 2 casas decimais)
 * - cargo: Cargo do funcionário na empresa (máximo 50 caracteres)
 *
 * @author Giovanni Leopoldo Rozza
 * @version 1.0
 * @date 12/02/2025
 *
 * @see Entity
 * @see Table
 * @see Data
 * @see Departamento
 * @see NamedQuery
 * @see NamedNativeQuery
 */

package com.utfpr.backend_departamento_funcionario.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name="funcionario")
// item 09
@NamedQuery(
        name = "Funcionario.byQtdDependentes",
        query = "from Funcionario f where f.qtdDependentes = ?1")

//item 10
@NamedNativeQuery(
        name = "Funcionario.byNomeParcial",
        query = "SELECT * FROM funcionario WHERE UPPER(nome_funcionario) LIKE CONCAT('%', UPPER( ?1 ), '%')",
        resultClass = Funcionario.class)
@Data
public class Funcionario  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cod_funcionario",nullable = false)
    private Long id;

    @Column(name="nome_funcionario",nullable = false,length = 100)
    private String nomeFuncionario;

    @ManyToOne
    @JoinColumn(name="cod_departamento",nullable = false)
    private Departamento departamento ;

    @Column(name="qtd_dependentes")
    private Integer qtdDependentes;

    @Column(name="salario",precision = 7, scale = 2, nullable = false)
    private BigDecimal salario;

    @Column(name="cargo",nullable = false,length = 50)
    private String cargo;


}
