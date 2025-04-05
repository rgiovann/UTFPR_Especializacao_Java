package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.entity.Cargo;
import org.example.repository.CargoRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    @Autowired
    private  CargoRepository cargoRepository;

    public Optional<Cargo> buscaCargo(Long id){
        return cargoRepository.findById(id);
    }

    public Cargo salvarCargo(Cargo cargo){
        return cargoRepository.save(cargo);
    }

    public List<Cargo> listarCargos() {

        return cargoRepository.findAll();

    }

    public void deletarCargo(Long id) {

        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado com o ID: " + id));

        try {
            cargoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir o cargo pois existem funcionários associados. " +
                    "Remova todos os funcionários deste cargo antes de excluí-lo.");
        }
    }
}
