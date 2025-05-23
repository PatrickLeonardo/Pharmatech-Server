package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
