package server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.constraints.Null;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ResponseEntity<Null> Home() {

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
