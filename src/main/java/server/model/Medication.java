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

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDosagem() {
        return this.dosagem;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public String getImagemDoMedicamento() {
        return this.imagemDoMedicamento;
    }

    public Double getPreco() {
        return this.preco;
    }

}
