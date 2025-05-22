package server.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
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
    public ResponseEntity<String> findAll() {

        final JSONArray jsonArray = new JSONArray();

        final List<Medication> listOfMedications = medicationsRepository.findAll();
        for(final Medication medication : listOfMedications) {
            
            final Map<String, Object> map = new LinkedHashMap<>();
             
            map.put("id", medication.getId());
            map.put("nome", medication.getNome());
            map.put("dosagem", medication.getDosagem());
            map.put("descricao", medication.getDescricao());
            map.put("imagemDoMedicamento", medication.getImagemDoMedicamento());
            map.put("preco", medication.getPreco());
            
            jsonArray.put(new JSONObject(map));
            
        }

        return ResponseEntity.ok(jsonArray.toString());

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
