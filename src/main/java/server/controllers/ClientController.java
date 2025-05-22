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

    @GetMapping(path = "findUserByCPFAndPassword")
    public ResponseEntity<Null> findUserByCPFAndPassword(@Valid @RequestParam final String CPF, @Valid @RequestParam final String password) {
        
        if(clientRepository.findByCPF(CPF) != null) {
            
            if(userRepository.findByCpfAndSenha(CPF, password) != null) {  
                return new ResponseEntity<>(HttpStatus.FOUND); // Status Code 302
            } else return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Status Code 404
            
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Status Code 404
        
    }

    @GetMapping(path = "findClientIdByCPF")
    public ResponseEntity<Long> findClientIdByCPF(@Valid @RequestParam final String CPF) {

        Client client = clientRepository.findByCPF(CPF);

        return ResponseEntity.ok(client.getId());

    }

    @PostMapping(path = "newClient")
    public ResponseEntity<Null> newClient(@Valid @RequestBody final User user, final BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
        }
        
        System.out.println(user.getCPF());

        user.setTipoDeUsuario("Cliente");
        userRepository.save(user);

        final Client client = new Client();
        client.setCPF(user.getCPF());

        clientRepository.save(client); 

        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

}
