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

    public void setId(int id) {
        this.id = id;
    } 

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getId(){
        return this.id;
    }

    public int getIdMedicamento() {
        return this.idMedicamento;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public int getIdCliente() {
        return this.idCliente;
    }

}
