package ar.edu.utn.frc.tup.lciii.dtos.common;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class SorteoForUpdateDTO implements Serializable {

    public LocalDate fecha;

}
