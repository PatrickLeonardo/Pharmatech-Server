package server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbUsuarios")
@Data
public class User {

    @Id
    @Column(name="cpf", unique = true, nullable = false)
    private String cpf;

    @Column(name="nome")
    private String nome;

    @Column(name="senha")
    private String senha;

    @Column(name="telefone")
    private String telefone;

    @Column(name="endereco")
    private String endereco;

    @Column(name="tipoDeUsuario")
    private String tipoDeUsuario;

}
