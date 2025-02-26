/**
 * Serviço responsável pelas operações de negócio relacionadas a Departamentos.
 *
 * Esta classe implementa a camada de serviço para Departamentos, fazendo a
 * intermediação entre o controlador e o repositório. Utiliza injeção de
 * dependência via construtor para maior testabilidade e segurança.
 *
 * Funcionalidades disponíveis:
 * - Listagem de todos os departamentos
 * - Consulta do primeiro departamento cadastrado no sistema
 * - Inclusão de um funcionário em um departamento
 *
 * @author Giovanni Leopoldo Rozza
 * @version 1.1
 * @since 25/02/2025
 */

package com.utfpr.backend_departamento_funcionario.service;

import com.utfpr.backend_departamento_funcionario.entity.Departamento;
import com.utfpr.backend_departamento_funcionario.entity.Funcionario;
import com.utfpr.backend_departamento_funcionario.repository.DepartamentoRepository;
import com.utfpr.backend_departamento_funcionario.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.net.DatagramPacket;
import java.util.List;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final FuncionarioRepository funcionarioRepository;

    // Constructor injection instead of @Autowired
    public DepartamentoService(DepartamentoRepository departamentoRepository, FuncionarioRepository funcionarioRepository) {
        this.departamentoRepository = departamentoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Departamento> listarTodosDepartamentos() {
        return departamentoRepository.findAll();

    }

    public Departamento listaPrimeiroDepartamentoCadastrado() {
        return departamentoRepository.listaPrimeiroDepartamentoCadastrado();
    }

    //*********************************************************************
    // INICIO DOS MÉTODOS DA Atividade JPA - Manipular Dados e Transações
    //*********************************************************************

    // item 05
    public void incluirFuncionario(Departamento departamento, Funcionario funcionario) {
        departamentoRepository.save(departamento);
        funcionario.setDepartamento(departamento);
        funcionarioRepository.save(funcionario);
    }
}
