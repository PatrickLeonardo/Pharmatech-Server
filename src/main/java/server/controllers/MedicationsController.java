package server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PutMapping(path = "updateQuantity/{id}/{newQuantity}")
    public ResponseEntity<String> updateQuantity(@Valid @PathVariable Long id, @Valid @PathVariable int newQuantity) {

        Medication medication = medicationsRepository.findById(id).get();
        medication.setQuantidadeDisponivel(newQuantity);
        medicationsRepository.save(medication);

        return ResponseEntity.status(200).build();


    }

    @PostMapping(path = "newMedication")
    public ResponseEntity<Object> newMedication(@Valid @RequestBody final Medication medication) {

        System.out.println(medication.getNome());
        medicationsRepository.save(medication);
        
        return ResponseEntity.status(202).build();

    }


}
