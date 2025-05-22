package server.controllers;

import java.util.List;

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
import server.model.Cart;
import server.repository.CartRepository;

@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartRepository cartRepository;

    @PostMapping(path = "insertItemInCart")
    public ResponseEntity<HttpStatus> insertItemInCart(@Valid @RequestBody final Cart cart, final BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }

        cartRepository.save(cart);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);

    }

    @GetMapping(path = "findByClientId")
    public ResponseEntity<List<Cart>> findByClientId(@Valid @RequestParam final int clientId) {

        List<Cart> cart = cartRepository.findByIdCliente(clientId);

        for (Cart item : cart) {
            System.out.println(item.getId());
        }

        return ResponseEntity.ok(cart);


    } 

}
