package br.com.jeison.hotel.app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hospede")
public class Hospede implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospede_id_sequence")
    @SequenceGenerator(name = "hospede_id_sequence", sequenceName = "hospede_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "documento", nullable = false, length = 11)
    private String documento;

    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    public Hospede() {

    }

    public Hospede(Long id, String nome, String documento, String telefone) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Hospede{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", documento='" + documento + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }

}

