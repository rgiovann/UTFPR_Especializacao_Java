package br.edu.utfpr.sistema.bd.prova_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.sistema.bd.prova_final.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
