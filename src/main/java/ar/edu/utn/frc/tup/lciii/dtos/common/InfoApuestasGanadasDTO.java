package ar.edu.utn.frc.tup.lciii.dtos.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
public class InfoApuestasGanadasDTO implements Serializable {

    public long id_sorteo;

    public LocalDate fecha_sorteo;

    public int totalApuestas;

    public int totalPagado;

    public int totalReserva;

}
