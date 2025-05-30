package server.controllers;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Stream;
import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import server.model.Pharmacist;
import server.model.Storekeeper;
import server.model.User;
import server.repository.ClientRepository;
import server.repository.PharmacistRepository;
import server.repository.StorekeeperRepository;
import server.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ClientRepository clientRepository; 

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private StorekeeperRepository storekeeperRepository;

    @GetMapping(path = "find/{cpf}/{password}")
    public ResponseEntity<Void> find(@Valid @PathVariable final String cpf, @Valid @PathVariable final String password) { 
        
        return ResponseEntity.status((userRepository.findByCpfAndSenha(cpf, password) != null) ? 302 : 404).build();
         
    }

    @GetMapping(path = "findName/{cpf}")
    public ResponseEntity<String> findName(@Valid @PathVariable final String cpf) {

        User user = userRepository.findByCpf(cpf);
        if(user != null)
            return ResponseEntity.status(302).body(user.getNome());

        else return ResponseEntity.status(404).build();

    }

    @GetMapping(path = "findEmployees")
    public ResponseEntity<List<User>> findEmployees() {

        List<User> almoxerifes = userRepository.findByTipoDeUsuario("Almoxerife");
        List<User> farmaceuticos = userRepository.findByTipoDeUsuario("Farmaceutico");

        List<User> employees = Stream.concat(almoxerifes.stream(), farmaceuticos.stream()).toList();

        return ResponseEntity.status(302).body(employees);

    }

    @GetMapping(path = "getUser/{cpf}/{password}")
    public ResponseEntity<User> getUser(@Valid @PathVariable final String cpf, @Valid @PathVariable final String password) {
        
        User findedUser = userRepository.findByCpfAndSenha(cpf, password);
        
        return ResponseEntity.status((findedUser != null) ? 302 : 404).body(findedUser);

    }

    @PostMapping(path = "newEmployee")
    public ResponseEntity<Object> newEmployee(@Valid @RequestBody final User user) {

        userRepository.save(user);


        if(user.getTipoDeUsuario().equals("Farmaceutico")) {
            
            Pharmacist pharmacist = new Pharmacist();
            pharmacist.setCpf(user.getCpf());

            pharmacistRepository.save(pharmacist);
            
        } else {
            
            Storekeeper storekeeper = new Storekeeper();
            storekeeper.setCpf(user.getCpf());

            storekeeperRepository.save(storekeeper);

        }
        
        return ResponseEntity.status(302).build();

    }

    @PutMapping(path = "update/{cpf}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @Valid @PathVariable String cpf) {

        User existentUser = userRepository.findByCpf(cpf);
        
        existentUser.setNome(user.getNome());
        existentUser.setSenha(user.getSenha());
        existentUser.setTelefone(user.getTelefone());
        existentUser.setEndereco(user.getEndereco());

        userRepository.save(existentUser);

        return ResponseEntity.ok(existentUser);

    }

    @DeleteMapping(path = "deleteUser/{cpf}")
    @Transactional
    public ResponseEntity<Object> deleteUser(@Valid @PathVariable String cpf) {

        User user = userRepository.findByCpf(cpf);
        String TipoDeUsuario = user.getTipoDeUsuario();

        if(TipoDeUsuario.equals("Farmaceutico")) {
            
            pharmacistRepository.deleteByCpf(cpf);
            
        } else {

            storekeeperRepository.deleteByCpf(cpf);

        }
        
        userRepository.deleteByCpf(cpf);

        return ResponseEntity.status(200).build();

    }

}
