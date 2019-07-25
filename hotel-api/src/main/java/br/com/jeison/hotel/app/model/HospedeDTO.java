package br.com.jeison.hotel.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.jeison.hotel.app.entity.Hospede;

public class HospedeDTO {

    private Long id;

    @NotEmpty(message = "O nome do hospode não pode ser vazio!")
    private String nome;

    @NotEmpty(message = "É necessário que um documento do hospede seja informado!")
    @Size(max = 11, message = "O documento deve ter 8 digitos para RG ou 11 para CPF")
    private String documento;

    @NotEmpty(message = "O telefone do hospede não pode ser vazio!")
    private String telefone;

    public HospedeDTO(Long id, String nome, String documento, String telefone) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.telefone = telefone;
    }

    public static HospedeDTO of(Hospede hospede) {
        return new HospedeDTO(hospede.getId(),
                hospede.getNome(),
                hospede.getDocumento(),
                hospede.getTelefone());
    }

    public static Page<HospedeDTO> of(Page<Hospede> page, Pageable paginacao) {
        List<HospedeDTO> dtos = new ArrayList<>();

        for (Hospede hospede : page) {
            dtos.add( of(hospede) );
        }

        return new PageImpl<HospedeDTO>(dtos, paginacao, page.getTotalElements());
    }

    public static List<HospedeDTO> of(List<Hospede> hospedes) {
        List<HospedeDTO> dtos = new ArrayList<>();

        for (Hospede hospede : hospedes) {
            dtos.add( of(hospede) );
        }

        return dtos;
    }

    public Hospede toObject() {
        return new Hospede(id, nome, documento, telefone);
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

}

