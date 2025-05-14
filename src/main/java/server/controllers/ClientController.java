package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import server.model.User;
import server.repository.ClientRepository;
import server.repository.UserRepository;
import server.model.Client;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<Null> TestEndpoint() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "newClient")
    public ResponseEntity<Null> newClient(@Valid @RequestBody User user, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        }
        
        user.setTipoDeUsuario("Cliente");
        userRepository.save(user);

        Client client = new Client();
        client.setCPF(user.getCPF());

        clientRepository.save(client); 

        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

}
