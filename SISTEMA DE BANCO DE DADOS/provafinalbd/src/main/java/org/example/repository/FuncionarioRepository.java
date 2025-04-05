package org.example.repository;

import org.example.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Query(value = "SELECT * FROM funcionario ORDER BY nome", nativeQuery = true)
    List<Funcionario> listaNomeFuncOrdemAlfabCresc();

    @Query("SELECT COUNT(f) FROM Funcionario f")
    Long totalFuncionarios();

}
