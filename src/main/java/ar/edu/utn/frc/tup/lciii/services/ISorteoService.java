package ar.edu.utn.frc.tup.lciii.services;


import ar.edu.utn.frc.tup.lciii.dtos.common.GetSorteoResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.InfoApuestasGanadasDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.NuevoSorteoResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.RequestAltaApuestaDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseAltaApuestaDTO;

import java.util.List;

public interface ISorteoService {

    NuevoSorteoResponseDTO altaSorteo();
    ResponseAltaApuestaDTO altaApuesta(RequestAltaApuestaDTO requestAltaApuestaDTO0);

    List<GetSorteoResponseDTO> obtenerTodosLosSorteos();

    InfoApuestasGanadasDTO obtenerInfoApuestasGanadas(long idSorteo);




}
