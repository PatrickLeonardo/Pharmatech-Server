package server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import server.model.Medication;
import server.repository.MedicationsRepository;

@Controller
@RequestMapping("/medications")
public class MedicationsController {

    @Autowired
    private MedicationsRepository medicationsRepository;
    
    @GetMapping
    public ResponseEntity<List<Medication>> findAll() {

        final List<Medication> listOfMedications = medicationsRepository.findAll();

        return ResponseEntity.ok(listOfMedications);

    }

    @GetMapping(path = "findByNome")
    public ResponseEntity<List<Medication>> findByNome(@Valid @RequestParam final String nome) {
         
        final List<Medication> medications = medicationsRepository.findByNomeLike("%" + nome + "%");
        
        return ResponseEntity.ok(medications);

    }

    @GetMapping(path = "findById")
    public ResponseEntity<Optional<Medication>> findById(@Valid @RequestParam Long id) {

        final Optional<Medication> medication = medicationsRepository.findById(id);

        return ResponseEntity.ok(medication);

    }

}
