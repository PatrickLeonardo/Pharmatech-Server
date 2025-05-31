package server.controllers;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import server.model.Cart;
import server.model.Client;
import server.model.Medication;
import server.model.Reservation;
import server.repository.CartRepository;
import server.repository.ClientRepository;
import server.repository.MedicationsRepository;
import server.repository.ReservationRepository;

@Controller
@RequestMapping(path = "/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MedicationsRepository medicationsRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping()
    public ResponseEntity<String> findAll() {
         
        final List<Reservation> reservations = reservationRepository.findAll();
        final JSONArray callBack = new JSONArray();

        for (final Reservation reservation : reservations) {

            final JSONObject newFormatedReservationJsonObject = new JSONObject();
            newFormatedReservationJsonObject.put("protocolo", reservation.getProtocolo());

            final JSONArray medicamentos = new JSONArray(reservation.getMedicamentos());

            final JSONArray medicamentosObject = new JSONArray();

            for (final Object medicamento : medicamentos) {
                
                final JSONObject idMedicamentoAndQuantidadeReservadaJSONObject = new JSONObject("" + medicamento);
                
                final Integer idMedicamento =  idMedicamentoAndQuantidadeReservadaJSONObject.getInt("idMedicamento");
                final Medication medication = medicationsRepository.getById(idMedicamento.longValue());
                
                final JSONObject medicationObject = new JSONObject();
                medicationObject.put("nome", medication.getNome());
                medicationObject.put("dosagem", medication.getDosagem());
                medicationObject.put("preco", medication.getPreco());
                medicationObject.put("precisaDeReceita", medication.getPrecisaDeReceita());
                medicationObject.put("quantidadeReservada", idMedicamentoAndQuantidadeReservadaJSONObject.get("quantidadeReservada"));

                medicamentosObject.put(medicationObject);

            }

            newFormatedReservationJsonObject.put("medicamentos", medicamentosObject);
            newFormatedReservationJsonObject.put("valorTotal", reservation.getValorTotal());
            callBack.put(newFormatedReservationJsonObject);
            
        }

        return ResponseEntity.status(200).body(callBack.toString());

    }

    @PostMapping(path = "newReservation")
    @Transactional
    public ResponseEntity<Object> newReservation(@Valid @RequestParam final String cpf, @Valid @RequestParam final String protocolo) {

        Reservation reservation = new Reservation();
        reservation.setProtocolo(protocolo);

        double totalValue = 0;
        
        JSONArray medications = new JSONArray();

        Client client = clientRepository.findByCpf(cpf); 
        
        List<Cart> carts = cartRepository.findByIdCliente(client.getId());

        for (Cart cart : carts) {

            JSONObject medicationObject = new JSONObject();
            //Optional medication = medicationsRepository.findById(cart.getIdMentiment());

            Integer medicamentoId = cart.getIdMedicamento();

            totalValue += medicationsRepository.findById(medicamentoId.longValue()).get().getPreco();
            medicationObject.put("idMedicamento", cart.getIdMedicamento());
            medicationObject.put("quantidadeReservada", cart.getQuantidade());

            medications.put(medicationObject);
            
        }

        reservation.setMedicamentos(medications.toString());
        reservation.setValorTotal(totalValue);    

        reservationRepository.save(reservation);
        
        cartRepository.deleteByIdCliente(client.getId());

        return ResponseEntity.status(201).build();

    }

    @DeleteMapping(path = "delete/{protocolo}")
    @Transactional
    public ResponseEntity<Object> delete(@Valid @PathVariable final String protocolo) {
        
        reservationRepository.deleteByProtocolo(protocolo);

        return ResponseEntity.status(200).build();

    }

}
