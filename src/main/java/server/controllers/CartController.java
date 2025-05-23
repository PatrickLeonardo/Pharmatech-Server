package server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

        Cart amazenededCart = cartRepository.findByIdMedicamento(cart.getIdMedicamento());

        if(amazenededCart == null) {
            
            cartRepository.save(cart);
            
        } else {

            amazenededCart.setQuantidade(amazenededCart.getQuantidade() + cart.getQuantidade());
            cartRepository.save(amazenededCart);
            
        }
        
        return ResponseEntity.ok(HttpStatus.ACCEPTED);

    }

    @DeleteMapping(path = "deleteItemInCartById")
    public ResponseEntity<HttpStatus> deleteItemInCartById(@Valid @RequestParam final Long cartId) {
        
        cartRepository.deleteById(cartId);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);

    }

    @GetMapping(path = "findByClientId")
    public ResponseEntity<List<Cart>> findByClientId(@Valid @RequestParam final int clientId) {

        final List<Cart> cart = cartRepository.findByIdCliente(clientId);

        return ResponseEntity.ok(cart);

    }

    @PutMapping(path = "increseOrDecreaseItemInCart")
    public ResponseEntity<Cart> increseItemInCart(@Valid @RequestParam final Long cartId, @Valid @RequestParam final int quantity) {
        
        if(quantity > 0) {
            
            final Optional<Cart> item = cartRepository.findById(cartId);
            
            final Cart itemCart = item.get();
            itemCart.setQuantidade(quantity);
            cartRepository.save(itemCart);
            
            return ResponseEntity.ok(itemCart);
            
        } else {

            cartRepository.deleteById(cartId);
            return ResponseEntity.ok(null);
            
        }

    }

}
