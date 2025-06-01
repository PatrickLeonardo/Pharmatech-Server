package server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import server.model.Client;
import server.model.User;
import server.repository.ClientRepository;
import server.repository.UserRepository;

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

    @GetMapping(path = "findClientByCPF/{CPF}")
    public ResponseEntity<Client> findClientIdByCPF(@Valid @PathVariable final String CPF) {

        Client client = clientRepository.findByCpf(CPF);

        return ResponseEntity.ok(client);

    }

    @PostMapping(path = "newClient")
    public ResponseEntity<Null> newClient(@Valid @RequestBody final User user, final BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        }
        
        if(userRepository.findByCpf(user.getCpf()) != null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        user.setTipoDeUsuario("Cliente");
        userRepository.save(user);

        final Client client = new Client();
        client.setCpf(user.getCpf());

        clientRepository.save(client); 

        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

}
