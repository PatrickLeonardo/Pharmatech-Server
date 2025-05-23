package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import server.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByCpfAndSenha(String CPF, String password);

    User findByCpf(String CPF);
    
}
