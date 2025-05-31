package server.model;

import com.fasterxml.jackson.annotation.JsonRawValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbReservas")
@Data
public class Reservation {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "protocolo")
    private String protocolo;

    @JsonRawValue
    @Column(name = "medicamentos", columnDefinition = "json")
    private String medicamentos;

}
