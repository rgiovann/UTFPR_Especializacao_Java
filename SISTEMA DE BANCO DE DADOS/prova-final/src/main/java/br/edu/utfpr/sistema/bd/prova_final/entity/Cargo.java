package br.edu.utfpr.sistema.bd.prova_final.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cargo")
@Data
//Exclui a coleção do equals e hashCode da collection
@EqualsAndHashCode(exclude = "funcionarios") 

public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cod_cargo" , nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String cargo;

    @OneToMany(mappedBy = "cargo",  fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios;
    
    @Override
    public String toString() {
        return "Cargo{" +
               "codCargo=" + id +
               ", cargo=" + cargo + '\'' +
               ", funcionarios=" + funcionarios +
               '}';
    }
}