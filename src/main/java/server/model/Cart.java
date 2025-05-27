package server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbCarrinho")
@Data
public class Cart {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "idMedicamento")
    private int idMedicamento;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "idCliente")
    private int idCliente;

}
