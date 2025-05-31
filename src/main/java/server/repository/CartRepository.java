package server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import server.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByIdCliente(Long idCliente);
    
    List<Cart> findById(int id);

    Cart findByIdMedicamentoAndIdCliente(int id, int clientId);
    
    @Transactional
    Long deleteByIdCliente(Long idCliente);

}
