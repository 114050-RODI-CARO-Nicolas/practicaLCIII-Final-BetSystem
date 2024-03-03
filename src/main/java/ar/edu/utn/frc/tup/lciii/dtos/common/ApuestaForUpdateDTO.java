package ar.edu.utn.frc.tup.lciii.dtos.common;

import lombok.Getter;

import java.io.Serializable;


@Getter
public class ApuestaForUpdateDTO implements Serializable {

    String id_cliente;

    Integer numero;

    Integer montoApostado;
}
