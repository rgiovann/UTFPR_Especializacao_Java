/**
 * Interface de repositório para a entidade Departamento.*
 * Esta interface estende JpaRepository para fornecer operações básicas de CRUD
 * e outras funcionalidades de persistência para a entidade Departamento.
 * Além dos métodos padrão do JpaRepository, oferece consultas personalizadas
 * para necessidades específicas.
 * Consultas personalizadas:
 * - listaPrimeiroDepartamentoCadastrado(): Retorna o primeiro departamento cadastrado
 *   com base no código do departamento em ordem ascendente
 *
 * @author Giovanni Leopoldo Rozza
 * @version 1.0
 * @date 12/02/2025
 *
 * @see JpaRepository
 * @see Departamento
 * @see Query
 */

package com.utfpr.backend_departamento_funcionario.repository;

import com.utfpr.backend_departamento_funcionario.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartamentoRepository extends JpaRepository<Departamento,Long> {

    // item 3
    @Query(value = "SELECT * FROM departamento ORDER BY cod_departamento ASC LIMIT 1", nativeQuery = true)
    Departamento listaPrimeiroDepartamentoCadastrado();
}


