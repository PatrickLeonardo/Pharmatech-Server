package server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import server.model.Storekeeper;

@Repository
public interface StorekeeperRepository extends JpaRepository<Storekeeper, Long>{
    
    Long deleteByCpf(String cpf);

}
