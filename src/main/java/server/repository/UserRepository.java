package server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import server.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findByCpfAndSenha(String CPF, String password);

    User findByCpf(String CPF);
    
    List<User> findByTipoDeUsuario(String tipoDeUsuario);

    @Transactional
    Long deleteByCpf(String cpf);

}
