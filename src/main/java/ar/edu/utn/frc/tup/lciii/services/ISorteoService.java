package ar.edu.utn.frc.tup.lciii.services;


import ar.edu.utn.frc.tup.lciii.domain.Sorteo;
import ar.edu.utn.frc.tup.lciii.dtos.common.RequestAltaApuestaDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseAltaApuestaDTO;
import org.springframework.stereotype.Service;

public interface ISorteoService {

    Sorteo altaSorteo();
    ResponseAltaApuestaDTO altaApuesta(RequestAltaApuestaDTO requestAltaApuestaDTO0);
}
