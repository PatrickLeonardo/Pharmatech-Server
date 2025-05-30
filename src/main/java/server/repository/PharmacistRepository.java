package server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import server.model.Pharmacist;

@Repository
public interface PharmacistRepository extends JpaRepository<Pharmacist, Long>{
    
    Long deleteByCpf(String cpf);

}
