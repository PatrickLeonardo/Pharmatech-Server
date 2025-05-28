package server.controllers;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import server.model.User;
import server.repository.ClientRepository;
import server.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ClientRepository clientRepository; 

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "find/{cpf}/{password}")
    public ResponseEntity<Void> find(@Valid @PathVariable final String cpf, @Valid @PathVariable final String password) { 
        
        if(clientRepository.findByCPF(cpf) != null)
            return ResponseEntity.status((userRepository.findByCpfAndSenha(cpf, password) != null) ? 302 : 404).build();
        
        else return ResponseEntity.status(404).build(); // Status Code 404    
        
    }

    @GetMapping(path = "findName/{cpf}")
    public ResponseEntity<String> findName(@Valid @PathVariable final String cpf) {

        User user = userRepository.findByCpf(cpf);
        if(user != null)
            return ResponseEntity.status(302).body(user.getNome());

        else return ResponseEntity.status(404).build();

    }

    @GetMapping(path = "getUser/{cpf}/{password}")
    public ResponseEntity<User> getUser(@Valid @PathVariable final String cpf, @Valid @PathVariable final String password) {
        
        User findedUser = userRepository.findByCpfAndSenha(cpf, password);
        
        return ResponseEntity.status((findedUser != null) ? 302 : 404).body(findedUser);

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

}
