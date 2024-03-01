package ar.edu.utn.frc.tup.lciii.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Setter
@Getter
public class GetSorteoResponseDTO implements Serializable {

    private LocalDate fecha;
    private int totalEnReserva;

    private List<HashMap<Long, Integer>> numerosSorteados = new ArrayList<>();

    public void agregarNumeroSorteado(HashMap<Long, Integer> numeroSorteado){

        numerosSorteados.add(numeroSorteado);

    }

    public void quitarNumeroSorteado(HashMap<Long, Integer> numeroSorteado){
        numerosSorteados.remove(numeroSorteado);
    }






}
