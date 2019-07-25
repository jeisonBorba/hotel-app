package br.com.jeison.hotel.app.common;

import java.math.BigDecimal;
import java.time.LocalTime;

public class CheckinUtil {
	
    public final static BigDecimal DIA_SEMANA = new BigDecimal(120);
    public final static BigDecimal FINAL_SEMANA = new BigDecimal(150);
    public final static BigDecimal CARRO_DIA_SEMANA = new BigDecimal(15);
    public final static BigDecimal CARRO_FINAL_SEMANA = new BigDecimal(20);
    public final static LocalTime LIMITE_TEMPO = LocalTime.of(16,30,0,0);

}
