package server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbMedicamentos")
@Data
public class Medication {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "dosagem")
    private String dosagem;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "imagemDoMedicamento")
    private String imagemDoMedicamento;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "quantidadeDisponivel")
    private int quantidadeDisponivel;

    @Column(name = "precisaDeReceita")
    private Boolean precisaDeReceita;

}
