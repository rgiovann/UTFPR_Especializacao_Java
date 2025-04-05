package br.edu.utfpr.sistema.bd.prova_final.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "funcionario")
@Data
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cod_funcionario" , nullable = false)

    private Long id;

    @Column(length = 70, nullable = false)
    private String nome;

    @Column(length = 10, nullable = false)
    private String sexo;

    @Column(length = 20)
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "cod_cargo", nullable = false)
    private Cargo cargo;
}
