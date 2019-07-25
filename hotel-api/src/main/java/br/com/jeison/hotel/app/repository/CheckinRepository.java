package br.com.jeison.hotel.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.jeison.hotel.app.entity.Checkin;
import br.com.jeison.hotel.app.entity.Hospede;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long> {
	
	List<Checkin> findByDataSaidaLessThanEqual(LocalDateTime now);
	
    @Query("SELECT c FROM Checkin c WHERE c.dataEntrada <= :now and (c.dataSaida is null or c.dataSaida >= :now)")
    List<Checkin> listHospedeInHotel(LocalDateTime now);
    
    @Query(nativeQuery = true,
    		value = "SELECT * FROM checkin "
    				+ "WHERE hospede_id in (:hospedes) "
    				+ "AND data_saida IS NOT NULL "
    				+ "ORDER BY data_saida DESC "
    				+ "LIMIT 1")
    List<Checkin> findUltimaHospedagemByHospedesIn(@Param("hospedes") List<Hospede> hospedes);
	
}