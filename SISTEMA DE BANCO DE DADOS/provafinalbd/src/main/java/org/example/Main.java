package org.example;

import org.example.entity.Cargo;
import org.example.entity.Funcionario;
import org.example.service.CargoService;
import org.example.service.FuncionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(FuncionarioService funcionarioService, CargoService cargoService) {
        return (arg) -> {

            Cargo cargo1 = new Cargo();
            cargo1.setCargo("Engenheiro de Software");
            cargo1 = cargoService.salvarCargo(cargo1);


            Cargo cargo2 = new Cargo();
            cargo2.setCargo("Analista de Dados");
            cargo2 = cargoService.salvarCargo(cargo2);


            Cargo cargo3 = new Cargo();
            cargo3.setCargo("Gerente de Projetos");
            cargo3 = cargoService.salvarCargo(cargo3);

            log.info("Cargos criados: {}, {}, {}", cargo1, cargo2, cargo3);

            Funcionario funcionario1 = new Funcionario();
            funcionario1.setNome("João Silva");
            funcionario1.setSexo("Masculino");
            funcionario1.setTelefone("99999-9999");
            funcionario1.setCargo(cargo1);
            funcionario1 = funcionarioService.salvarFuncionario(funcionario1);


            Funcionario funcionario2 = new Funcionario();
            funcionario2.setNome("Maria Souza");
            funcionario2.setSexo("Feminino");
            funcionario2.setTelefone("88888-8888");
            funcionario2.setCargo(cargo2);
            funcionario2 = funcionarioService.salvarFuncionario(funcionario2);


            Funcionario funcionario3 = new Funcionario();
            funcionario3.setNome("Carlos Oliveira");
            funcionario3.setSexo("Masculino");
            funcionario3.setTelefone("77777-7777");
            funcionario3.setCargo(cargo3);
            funcionario3 = funcionarioService.salvarFuncionario(funcionario3);
            log.info("Funcionários criados: {}, {}, {}", funcionario1, funcionario2, funcionario3);

            funcionarioService.deletarFuncionario(funcionario1.getId());
            log.info("Funcionário {} excluído", funcionario1.getId());

            cargoService.deletarCargo(cargo1.getId());
            log.info("Cargo {} excluído", cargo1.getId());

            log.info("");
            log.info("");

            log.info("LISTAGEM TODOS FUNCIONÁRIOS");
            List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
            if (funcionarios.isEmpty()) {
                log.info("Nenhum funcionário encontrado.");
            } else {
                for (Funcionario f : funcionarios) {
                    log.info(f.toString());
                }
            }
            log.info("");
            log.info("");

            log.info("LISTAGEM TODOS CARGOS");
            List<Cargo> cargos = cargoService.listarCargos();
            if (cargos.isEmpty()) {
                log.info("Nenhum cargo encontrado.");
            } else {
                for (Cargo c : cargos) {
                    log.info(c.toString());
                }
            }
            log.info("");
            log.info("");

            log.info("LISTAGEM TODOS FUNCIONÁRIOS ORDENADO NOME CRESCENTE");
            funcionarios = funcionarioService.listaFuncionarioOrdenadoNomeCrescente();
            if (funcionarios.isEmpty()) {
                log.info("Nenhum funcionário encontrado.");
            } else {
                for (Funcionario f : funcionarios) {
                    log.info(f.toString());
                }
            }
            log.info("");
            log.info("");

            log.info("TOTAL FUNCIONÁRIOS");
            log.info("Total funcionários: {}", funcionarioService.totalFuncionarios());


        };
    }
}