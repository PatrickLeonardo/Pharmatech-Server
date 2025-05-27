package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;
import server.repository.UserRepository;
import server.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository useRepository;

    @GetMapping(path = "findByCPF")
    public ResponseEntity<User> findByCpf(@Valid @RequestParam final String CPF) {

        return ResponseEntity.ok(useRepository.findByCpf(CPF));

    }

    @PutMapping(path = "update/{cpf}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @Valid @PathVariable String cpf) {

        User existentUser = useRepository.findByCpf(cpf);
        
        existentUser.setNome(user.getNome());
        existentUser.setSenha(user.getSenha());
        existentUser.setTelefone(user.getTelefone());
        existentUser.setEndereco(user.getEndereco());

        useRepository.save(existentUser);

        return ResponseEntity.ok(existentUser);

    }

}
