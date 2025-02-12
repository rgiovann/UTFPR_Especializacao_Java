/**
 * Classe principal da aplicação Spring Boot para gerenciamento de Departamentos e Funcionários.
 *
 * Esta classe inicializa a aplicação Spring Boot e configura um CommandLineRunner
 * para demonstrar as funcionalidades do sistema através de consultas ao banco de dados.
 * Utiliza Logger para exibir os resultados das operações no console.
 *
 * Demonstrações incluídas:
 * - Listagem completa de departamentos e funcionários
 * - Busca de funcionários por nome e quantidade de dependentes
 * - Listagem de funcionários por departamento
 * - Consulta do primeiro departamento cadastrado
 * - Consulta do funcionário com maior salário
 * - Listagem dos três maiores salários
 * - Funcionários sem dependentes
 * - Consultas por faixa salarial (JPQL e SQL nativo)
 * - Busca por quantidade de dependentes
 * - Busca por nome parcial
 *
 * @author Giovanni Leopoldo Rozza
 * @version 1.0
 * @date 12/02/2025
 *
 * @see SpringBootApplication
 * @see CommandLineRunner
 * @see FuncionarioService
 * @see DepartamentoService
 * @see Logger
 */

package com.utfpr.backend_departamento_funcionario;

import com.utfpr.backend_departamento_funcionario.entity.Departamento;
import com.utfpr.backend_departamento_funcionario.entity.Funcionario;
import com.utfpr.backend_departamento_funcionario.service.DepartamentoService;
import com.utfpr.backend_departamento_funcionario.service.FuncionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BackendDepartamentoFuncionarioApplication {

	private static final Logger log = LoggerFactory.getLogger(BackendDepartamentoFuncionarioApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendDepartamentoFuncionarioApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(FuncionarioService funcionarioService, DepartamentoService departamentoService){
		return (arg)->{
			log.info("");
			log.info("");
			log.info("a. LISTAGEM DE TODOS OS DEPARTAMENTOS");
			for(Departamento d:departamentoService.listarTodosDepartamentos())
			{
				log.info(d.toString());
			}

			log.info("");
			log.info("");
			log.info("b. LISTAGEM DE TODOS OS FUNCIONARIOS");
			for(Funcionario f:funcionarioService.listarTodosFuncionarios())
			{
				log.info(f.toString());
			}

			// Agora, buscar funcionários pelo nome "João" e 2 dependentes
			log.info("");
			log.info("");
			log.info("1. Listagem de FUNCIONARIOS COM nome = 'Clara Souza' E dependentes=2");
			List<Funcionario> funcionarios = funcionarioService.buscarFuncionarioPorNomeEDependentes("Clara Souza", 2);
			if (funcionarios.isEmpty()) {
				log.info("Nenhum funcionário encontrado.");
			} else {
				for (Funcionario f : funcionarios) {
					log.info(f.toString());
				}
			}

			log.info("");
			log.info("");
			log.info("2. LISTAGEM DE TODOS OS FUNCIONARIOS por DEPARTAMENTO id = 1");
			funcionarios = funcionarioService.listaTodosFuncionariosPorDepartamento(1L);
			if (funcionarios.isEmpty()) {
				log.info("Nenhum funcionário encontrado.");
			} else {
				for (Funcionario f : funcionarios) {
					log.info(f.toString());
				}
			}

			log.info("");
			log.info("");
			log.info("3. PRIMEIRO DEPARTAMENTO CADASTRADO");
			Optional<Departamento> departamentoOptional = Optional.ofNullable(departamentoService.listaPrimeiroDepartamentoCadastrado());

			departamentoOptional.ifPresentOrElse(
					departamento -> log.info("Departamento: {}", departamento ),
					() -> log.info("Nenhum departamento encontrado")
			);

			log.info("");
			log.info("");
			log.info("4. PRIMEIRO FUNCIONARIO COM MAIOR SALÁRIO");
			Optional<Funcionario> funcionarioOptional = Optional.ofNullable(funcionarioService.buscaUmFuncionarioMaiorSalario());

			funcionarioOptional.ifPresentOrElse(
					funcionario -> log.info("Funcionário: {}", funcionario),
					() -> log.info("Nenhum funcionário encontrado")
			);

			log.info("");
			log.info("");
			log.info("5. PRIMEIROS TRÊS FUNCIONARIO COM MAIOR SALÁRIO");
			funcionarios = funcionarioService.listaTresFuncionariosMaiorSalario();
			if (funcionarios.isEmpty()) {
				log.info("Nenhum funcionário encontrado.");
			} else {
				for (Funcionario f : funcionarios) {
					log.info(f.toString());
				}
			}

			log.info("");
			log.info("");
			log.info("6. LISTA FUNCIONARIOS SEM DEPENDENTES ORDEM CRESCENTE");
			funcionarios = funcionarioService.listaTodosFuncionariosSemDependentes();
			if (funcionarios.isEmpty()) {
				log.info("Nenhum funcionário encontrado.");
			} else {
				for (Funcionario f : funcionarios) {
					log.info(f.toString());
				}
			}


			log.info("");
			log.info("");
			log.info("7. LISTA FUNCIONARIOS COM SALARIO MAIOR QUE R$ 5.000,00 USANDO JPQL");
			funcionarios = funcionarioService.listaFuncionariosSalarioMaiorQue(new BigDecimal("5000.00"),0);
			if (funcionarios.isEmpty()) {
				log.info("Nenhum funcionário encontrado.");
			} else {
				for (Funcionario f : funcionarios) {
					log.info(f.toString());
				}
			}

			log.info("");
			log.info("");
			log.info("8. LISTA FUNCIONARIOS COM SALARIO MAIOR QUE R$ 5.000,00 USANDO QUERY NATIVA");
			funcionarios = funcionarioService.listaFuncionariosSalarioMaiorQue(new BigDecimal("5000.00"),1);
			if (funcionarios.isEmpty()) {
				log.info("Nenhum funcionário encontrado.");
			} else {
				for (Funcionario f : funcionarios) {
					log.info(f.toString());
				}
			}

			log.info("");
			log.info("");
			log.info("9. LISTA FUNCIONARIOS COM NUMERO DE DEPENDENTES IGUAL A 2");
			funcionarios = funcionarioService.findByQtdDependendentes(2);
			if (funcionarios.isEmpty()) {
				log.info("Nenhum funcionário encontrado.");
			} else {
				for (Funcionario f : funcionarios) {
					log.info(f.toString());
				}
			}

			log.info("");
			log.info("");
			log.info("10. LISTA FUNCIONARIOS CONTENDO A STRING 'SAL'");
			funcionarios = funcionarioService.findByNomeParcial("sal");
			if (funcionarios.isEmpty()) {
				log.info("Nenhum funcionário encontrado.");
			} else {
				for (Funcionario f : funcionarios) {
					log.info(f.toString());
				}
			}

		};

	}

}
