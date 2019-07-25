package br.com.jeison.hotel.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jeison.hotel.app.entity.Hospede;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {

    Hospede findByNomeContaining(String nome);

    Hospede findByDocumentoStartingWith(String documento);

    Hospede findByTelefoneLike(String telefone);

    Page<Hospede> findAll(Pageable paginacao);
    
    @Query("SELECT h from Hospede h "
    		+ "WHERE LOWER(h.nome) LIKE CONCAT(:filtro, '%') "
    		+ "OR LOWER(h.documento) LIKE CONCAT(:filtro, '%') "
    		+ "OR LOWER(h.telefone) LIKE CONCAT(:filtro, '%') ")
    List<Hospede> findByNomeOrDocumentoOrTelefone(@Param("filtro") String filtro);

}
