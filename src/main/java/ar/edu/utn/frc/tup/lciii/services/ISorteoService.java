package ar.edu.utn.frc.tup.lciii.services;


import ar.edu.utn.frc.tup.lciii.domain.GetSorteoResponseDTO;
import ar.edu.utn.frc.tup.lciii.domain.Sorteo;
import ar.edu.utn.frc.tup.lciii.dtos.common.NuevoSorteoResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.RequestAltaApuestaDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseAltaApuestaDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface ISorteoService {

    NuevoSorteoResponseDTO altaSorteo();
    ResponseAltaApuestaDTO altaApuesta(RequestAltaApuestaDTO requestAltaApuestaDTO0);

    List<GetSorteoResponseDTO> obtenerTodosLosSorteos();
}
