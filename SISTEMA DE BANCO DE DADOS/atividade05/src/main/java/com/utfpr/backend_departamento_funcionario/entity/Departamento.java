/**
 * Entidade que representa um Departamento no sistema.
 *
 * Esta classe é mapeada para a tabela "departamento" no banco de dados e contém
 * informações básicas sobre um departamento, incluindo seu código identificador
 * e nome.
 *
 * A classe utiliza Lombok (@Data) para geração automática de getters, setters,
 * equals, hashCode e toString.
 *
 * Atributos:
 * - id: Código único do departamento (auto-incremento)
 * - nomeDepartamento: Nome do departamento (máximo 100 caracteres)
 *
 * @author Giovanni Leopoldo Rozza
 * @version 1.0
 * @date 12/02/2025
 *
 * @see Entity
 * @see Table
 * @see Data
 */

package com.utfpr.backend_departamento_funcionario.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="departamento")
@Data
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cod_departamento",nullable = false)
    private Long id;

    @Column(name="nome_departamento",nullable = false,length = 100)
    private String nomeDepartamento;

}
