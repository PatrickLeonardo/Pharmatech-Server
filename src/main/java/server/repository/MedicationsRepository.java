package server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import server.model.Medication;

@Repository
public interface MedicationsRepository extends JpaRepository<Medication, Long> {
    
    List<Medication> findByNomeLike(String nome);

}
