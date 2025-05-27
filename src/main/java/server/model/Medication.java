package server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbMedicamentos")
public class Medication {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String dosagem;

    @Column
    private String descricao;

    @Column
    private String imagemDoMedicamento;

    @Column
    private Double preco;

    @Column
    private Boolean precisaDeReceita;

}
