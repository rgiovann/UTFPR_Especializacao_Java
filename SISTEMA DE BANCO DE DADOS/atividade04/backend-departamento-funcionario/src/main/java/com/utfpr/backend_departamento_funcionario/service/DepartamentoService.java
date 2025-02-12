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
 *
 * @author Giovanni Leopoldo Rozza
 * @version 1.0
 * @date 12/02/2025
 *
 * @see Service
 * @see DepartamentoRepository
 * @see Departamento
 */

package com.utfpr.backend_departamento_funcionario.service;

import com.utfpr.backend_departamento_funcionario.entity.Departamento;
import com.utfpr.backend_departamento_funcionario.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.net.DatagramPacket;
import java.util.List;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    // Constructor injection instead of @Autowired
    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public List<Departamento> listarTodosDepartamentos() {
        return departamentoRepository.findAll();

    }

    public Departamento listaPrimeiroDepartamentoCadastrado() {
        return departamentoRepository.listaPrimeiroDepartamentoCadastrado();
    }
}
