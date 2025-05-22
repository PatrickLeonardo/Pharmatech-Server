package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import server.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByIdCliente(int idCliente);
    
    List<Cart> findById(int id);

}
