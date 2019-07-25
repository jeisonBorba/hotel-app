package br.com.jeison.hotel.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "checkin")
public class Checkin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "checkin_id_sequence")
    @SequenceGenerator( name = "checkin_id_sequence", sequenceName = "checkin_id_seq", allocationSize = 1 )
    private Long id;

    @Column(name = "data_entrada", nullable = false)
    private LocalDateTime dataEntrada;

    @Column(name = "data_saida", nullable = true)
    private LocalDateTime dataSaida;

    @Column(name = "adicional_veiculo", nullable = false)
    private boolean adicionalVeiculo;

    @Column(name = "valor_pago", nullable = true)
    private BigDecimal valorPago;
    
    @Transient
    private BigDecimal valorUltimaHospedagem;

    @OneToOne(targetEntity = Hospede.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "hospede_id")
    private Hospede hospede;

    public Checkin() {

    }

    public Checkin(LocalDateTime dataEntrada, LocalDateTime dataSaida, boolean adicionalVeiculo, BigDecimal valorPago, Hospede hospede) {
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.adicionalVeiculo = adicionalVeiculo;
        this.valorPago = valorPago;
        this.hospede = hospede;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

	@Override
    public String toString() {
        return "Checkin{" +
                "id=" + id +
                ", dataEntrada=" + dataEntrada +
                ", dataSaida=" + dataSaida +
                ", adicionalVeiculo=" + adicionalVeiculo +
                ", valorPago=" + valorPago +
                ", hospede=" + hospede +
                '}';
    }
}

