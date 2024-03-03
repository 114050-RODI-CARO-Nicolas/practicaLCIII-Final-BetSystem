package ar.edu.utn.frc.tup.lciii.dtos.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ResponseApuestaDTO implements Serializable {

    LocalDate fecha_sorteo;
    String id_cliente;

    int numero;

    int montoApostado;

    String resultado;

}
