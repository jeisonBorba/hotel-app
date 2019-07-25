package br.com.jeison.hotel.app.service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jeison.hotel.app.common.CheckinUtil;
import br.com.jeison.hotel.app.entity.Checkin;
import br.com.jeison.hotel.app.model.CheckinDTO;
import br.com.jeison.hotel.app.repository.CheckinRepository;

@Service
public class CheckinService {

    private CheckinRepository checkinRepository;

    @Autowired
    public CheckinService(CheckinRepository checkinRepository) {
        this.checkinRepository = checkinRepository;
    }
    
    public List<CheckinDTO> findCheckinInOut(String status) {
    	
    	if (status.equalsIgnoreCase("in")) {
    		
    		return this.findHospedeInHotel();
    	}
    	
    	return this.findHospedeOutOfHotel();
    }
    
    public List<CheckinDTO> findHospedeInHotel() {
    	
    	List<Checkin> checkinsInHotel = this.checkinRepository.listHospedeInHotel(LocalDateTime.now());
    	
    	if (!checkinsInHotel.isEmpty()) {
    		
    		List<Checkin> ultimaHospedagem = this.getUltimaHospedagemByHospede(checkinsInHotel);
    		return CheckinDTO.of(this.calcularValorTotal(checkinsInHotel, ultimaHospedagem));
    	}
    	    	
        return new ArrayList<CheckinDTO>();
    }
    
    public List<CheckinDTO> findHospedeOutOfHotel() {
    	
    	List<Checkin> checkinsOutOfHotel = this.checkinRepository.findByDataSaidaLessThanEqual(LocalDateTime.now());
    	
    	if (!checkinsOutOfHotel.isEmpty()) {
    		
    		List<Checkin> ultimaHospedagem = this.getUltimaHospedagemByHospede(checkinsOutOfHotel);
    		return CheckinDTO.of(this.calcularValorTotal(checkinsOutOfHotel, ultimaHospedagem));
    	}
    	
    	return Collections.emptyList();
    }

    public Checkin save(CheckinDTO checkinDto) {
    	
    	if (checkinDto.getDataSaida() != null) {
    		Checkin checkin = checkinDto.toObject();
    		this.calcularValorTotal(checkin);
    		
    		return this.checkinRepository.save(checkin);
    	}
    	
    	return this.checkinRepository.save(checkinDto.toObject());
    }

    public Checkin update(CheckinDTO checkinDto) {
    	
    	if (checkinDto.getDataSaida() != null) {
    		Checkin checkin = checkinDto.toObject();
    		this.calcularValorTotal(checkin);
    		
    		return this.checkinRepository.save(checkin);
    	}
    	
        return this.checkinRepository.save(checkinDto.toObject());
    }
    
    private List<Checkin> getUltimaHospedagemByHospede(List<Checkin> checkins) {
    	
    	return this.checkinRepository.findUltimaHospedagemByHospedesIn(checkins.stream().map(o -> o.getHospede()).collect(Collectors.toList()));
    }
    
    private void calcularValorTotal(Checkin checkin) {
    	
        LocalDateTime dataEntrada = LocalDateTime.from(checkin.getDataEntrada());
        List<LocalDateTime> totalDiarias = new ArrayList<>();
        
        LocalDateTime dataReferenciaParaCalculo = this.getDataReferencia(checkin); 
        
        while (dataEntrada.isBefore(dataReferenciaParaCalculo)) {
        	totalDiarias.add(dataEntrada);
            dataEntrada = dataEntrada.plusDays(1);
        }

        for (LocalDateTime day : totalDiarias) {
        	this.calcularValorPorDia(checkin, day);
        }

        if (dataReferenciaParaCalculo.toLocalTime().isAfter(CheckinUtil.LIMITE_TEMPO)) {
        	this.calcularValorPorDia(checkin, dataReferenciaParaCalculo);
        }
        
    }

    private List<Checkin> calcularValorTotal(List<Checkin> checkinList, List<Checkin> ultimasHospedagem) {
    	
        if (checkinList != null) {
        	
            for (Checkin checkin : checkinList) {

            	this.calcularValorTotal(checkin);
            	this.serValorUltimaHospedagem(checkin, ultimasHospedagem);
            }
        }

        return checkinList;
    }
    
    private void serValorUltimaHospedagem(Checkin checkin, List<Checkin> ultimasHospedagem) {
    	
    	for (Checkin ultimaHospedagem : ultimasHospedagem) {
    		
    		if (checkin.getHospede().getId() == ultimaHospedagem.getHospede().getId()) {
    			checkin.setValorUltimaHospedagem(ultimaHospedagem.getValorPago());
    			break;
    		}
    		
    	}
    }

    private void calcularValorPorDia(Checkin checkin, LocalDateTime day) {
    	
        if (this.isFinalDeSemana(day)){

        	this.setValorPorDiaFinalDeSemana(checkin);            
            return;
        } 
        
        this.setValorPorDiaNormal(checkin);
       
    } 
    
    private void setValorPorDiaFinalDeSemana(Checkin checkin) {
    	
        if (checkin.getValorPago() == null) {
            checkin.setValorPago(new BigDecimal(0l));
        }

        checkin.setValorPago(checkin.getValorPago().add(CheckinUtil.FINAL_SEMANA));
        if (checkin.isAdicionalVeiculo()) {
            checkin.setValorPago(checkin.getValorPago().add(CheckinUtil.FINAL_SEMANA));
        }
    }
    
    private void setValorPorDiaNormal(Checkin checkin) {
    	
        if(checkin.getValorPago() == null){
            checkin.setValorPago(new BigDecimal(0l));
        }

        checkin.setValorPago(checkin.getValorPago().add(CheckinUtil.DIA_SEMANA));
        if(checkin.isAdicionalVeiculo()){
            checkin.setValorPago(checkin.getValorPago().add(CheckinUtil.CARRO_DIA_SEMANA));
        }
    }
    
    private boolean isFinalDeSemana(LocalDateTime day) {
    	
    	return day.getDayOfWeek().getValue() == DayOfWeek.SATURDAY.getValue()
                || day.getDayOfWeek().getValue() == DayOfWeek.SUNDAY.getValue();
    }
    
    private LocalDateTime getDataReferencia(Checkin checkin) {
    	
    	return (checkin.getDataSaida() != null) ? checkin.getDataSaida() : LocalDateTime.now();
    }
    
}
