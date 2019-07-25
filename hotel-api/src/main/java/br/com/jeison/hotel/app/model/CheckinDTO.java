package br.com.jeison.hotel.app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.jeison.hotel.app.entity.Checkin;
import br.com.jeison.hotel.app.entity.Hospede;

public class CheckinDTO {

	@NotNull(message = "Para realizar um checkin é necessário informar a data de entrada!")
    private LocalDateTime dataEntrada;

    private LocalDateTime dataSaida;

    private boolean adicionalVeiculo;

    private BigDecimal valorPago;

    @NotNull(message = "Para realizar um checkin é necessário informar um hospede!")
    private Hospede hospede;
    
    private BigDecimal valorUltimaHospedagem;
    
    public CheckinDTO() {
    	
    }

	public CheckinDTO(LocalDateTime dataEntrada, LocalDateTime dataSaida, boolean adicionalVeiculo,
			BigDecimal valorPago, Hospede hospede, BigDecimal valorUltimaHospedagem) {
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.adicionalVeiculo = adicionalVeiculo;
		this.valorPago = valorPago;
		this.hospede = hospede;
		this.valorUltimaHospedagem = valorUltimaHospedagem;
	}
	
	public Checkin toObject() {
		return new Checkin(dataEntrada, dataSaida, adicionalVeiculo, valorPago, hospede);
	}
	
	public static CheckinDTO of(Checkin checkin) {
		return new CheckinDTO(
				checkin.getDataEntrada(),
				checkin.getDataSaida(),
				checkin.isAdicionalVeiculo(),
				checkin.getValorPago(),
				checkin.getHospede(),
				checkin.getValorUltimaHospedagem());
	}
	
	public static List<CheckinDTO> of(List<Checkin> checkinList) {
		List<CheckinDTO> dtos = new ArrayList<>();
		
		for (Checkin checkin : checkinList) {
			dtos.add( of(checkin) );
		}
		
		return dtos;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDateTime getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}

	public boolean isAdicionalVeiculo() {
		return adicionalVeiculo;
	}

	public void setAdicionalVeiculo(boolean adicionalVeiculo) {
		this.adicionalVeiculo = adicionalVeiculo;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
	
	public BigDecimal getValorUltimaHospedagem() {
		return valorUltimaHospedagem;
	}
    
	public void setValorUltimaHospedagem(BigDecimal valorUltimaHospedagem) {
		this.valorUltimaHospedagem = valorUltimaHospedagem;
	}
}
