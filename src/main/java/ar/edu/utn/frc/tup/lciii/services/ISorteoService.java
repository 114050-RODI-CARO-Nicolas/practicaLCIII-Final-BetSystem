package ar.edu.utn.frc.tup.lciii.services;


import ar.edu.utn.frc.tup.lciii.domain.Apuesta;
import ar.edu.utn.frc.tup.lciii.dtos.common.*;

import java.time.LocalDate;
import java.util.List;

public interface ISorteoService {

    SorteoResponseDTO altaSorteo();

    List<SorteoResponseDTO> obtenerTodosLosSorteos();

    SorteoResponseDTO obtenerSorteoPorFecha(LocalDate fecha);

    SorteoResponseDTO modificarSorteoPorFecha(LocalDate fecha_sorteo, SorteoForUpdateDTO sorteoForUpdateDTO);

    boolean borrarSorteoPorFecha(LocalDate fecha);

    ResponseApuestaDTO altaApuesta(RequestAltaApuestaDTO requestAltaApuestaDTO0);

    List<ResponseApuestaDTO> obtenerTodasLasApuestas();

    ResponseApuestaDTO obtenerApuestaPorId(Long idApuesta);

    InfoApuestasGanadasDTO obtenerInfoApuestasGanadas(long idSorteo);








}
