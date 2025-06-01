package server.controllers;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import server.model.Cart;
import server.model.Client;
import server.model.Medication;
import server.repository.CartRepository;
import server.repository.ClientRepository;
import server.repository.MedicationsRepository;
import server.repository.UserRepository;

@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private CartRepository cartRepository;

    @Autowired 
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicationsRepository medicationsRepository;

    @GetMapping("findAll")
        public ResponseEntity<String> findAll() {

        final List<Cart> cartList = cartRepository.findAll();
        final JSONArray cartArray = new JSONArray();
        
        for(final Cart cart : cartList) {
            
            final JSONObject jsonObject = new JSONObject();

            final Long clientId = Long.parseLong("%d".formatted(cart.getIdCliente()));
            
            final Optional<Client> client = clientRepository.findById(clientId);
            final String clientCpf = client.get().getCpf();

            final String nomeUsuario = userRepository.findByCpf(clientCpf).getNome(); 
            final Optional<Medication> medication = medicationsRepository.findById(Long.parseLong("%d".formatted(cart.getIdMedicamento())));
            final String medicationName = medication.get().getNome();

            jsonObject.put("id", cart.getId());
            jsonObject.put("Cliente", nomeUsuario);
            jsonObject.put("CPF", clientCpf);
            jsonObject.put("Medicamento", medicationName);
            jsonObject.put("Quantidade", cart.getQuantidade());
            
            cartArray.put(jsonObject);

        }

        return ResponseEntity.ok(cartArray.toString());

    }

    @PostMapping(path = "insertItemInCart")
    public ResponseEntity<HttpStatus> insertItemInCart(@Valid @RequestBody final Cart cart, final BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }

        final Cart amazenededCart = cartRepository.findByIdMedicamentoAndIdCliente(cart.getIdMedicamento(), cart.getIdCliente());

        if(amazenededCart == null) {
            
            cartRepository.save(cart);
            
        } else {

            amazenededCart.setQuantidade(amazenededCart.getQuantidade() + cart.getQuantidade());
            cartRepository.save(amazenededCart);
            
        }
        
        return ResponseEntity.ok(HttpStatus.ACCEPTED);

    }

    @DeleteMapping(path = "deleteItemInCartById/{cartId}")
    public ResponseEntity<HttpStatus> deleteItemInCartById(@Valid @PathVariable final Long cartId) {
        
        cartRepository.deleteById(cartId);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);

    }

    @GetMapping(path = "findByClientId/{clientId}")
    public ResponseEntity<List<Cart>> findByClientId(@Valid @PathVariable final Long clientId) {

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
