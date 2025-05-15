package server.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import server.model.Medication;
import server.repository.MedicationsRepository;

@Controller
@RequestMapping("/medications")
public class MedicationsController {

    @Autowired
    private MedicationsRepository medicationsRepository;

    @GetMapping(path = "test")
    public ResponseEntity<String> testAll() throws IOException, URISyntaxException {
         
        URI uri = new URI("http://localhost:8080/medications");
        URL url = uri.toURL();
        URLConnection urlConnection = url.openConnection();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        JSONArray jsonArray = new JSONArray(bufferedReader.readLine());
        System.out.println(jsonArray.get(0));
        
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        
        Iterator<String> keys = jsonObject.keys();
        int i= 0;
        
        while (keys.hasNext()) {
            
            String key = keys.next();
            System.out.println(jsonObject.names().getString(i) + " : " + jsonObject.get(key));
            i++;

        }

        bufferedReader.close();

        return ResponseEntity.ok("");

    }

    @GetMapping
    public ResponseEntity<String> findAll() {

        JSONArray jsonArray = new JSONArray();

        List<Medication> listOfMedications = medicationsRepository.findAll();
        for(Medication medication : listOfMedications) {
            
            Map<String, Object> map = new LinkedHashMap<>();
             
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

}
