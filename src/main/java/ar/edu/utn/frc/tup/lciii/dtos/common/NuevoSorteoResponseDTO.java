package ar.edu.utn.frc.tup.lciii.dtos.common;

import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
public class NuevoSorteoResponseDTO implements Serializable {
    public LocalDate fecha;
    public Long id;
    public int totalEnReserva;

}
