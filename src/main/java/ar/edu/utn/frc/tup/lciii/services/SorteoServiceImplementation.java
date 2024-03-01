package ar.edu.utn.frc.tup.lciii.services;


import ar.edu.utn.frc.tup.lciii.domain.Apuesta;
import ar.edu.utn.frc.tup.lciii.domain.NumeroApostado;
import ar.edu.utn.frc.tup.lciii.domain.Sorteo;
import ar.edu.utn.frc.tup.lciii.dtos.common.RequestAltaApuestaDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseAltaApuestaDTO;
import ar.edu.utn.frc.tup.lciii.repositories.ApuestaRepository;
import ar.edu.utn.frc.tup.lciii.repositories.NumeroRepository;
import ar.edu.utn.frc.tup.lciii.repositories.SorteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SorteoServiceImplementation implements ISorteoService {

    @Autowired
    SorteoRepository sorteoRepository;

    @Autowired
    ApuestaRepository apuestaRepository;

    @Autowired
    NumeroRepository numeroRepository;


    LocalDate localDate= LocalDate.now();
    @Override
    public Sorteo altaSorteo() {
        Sorteo nuevoSorteo=new Sorteo();
        nuevoSorteo.setFecha(localDate);
        return sorteoRepository.save(nuevoSorteo);

    };

    @Override
    public ResponseAltaApuestaDTO altaApuesta(RequestAltaApuestaDTO requestAltaApuestaDTO) {


        Sorteo sorteoEncontrado=sorteoRepository.findByFecha(requestAltaApuestaDTO.getFecha_sorteo());
        Apuesta apuesta = new Apuesta();

        apuesta.setId_cliente(requestAltaApuestaDTO.getId_cliente());
        apuesta.setNumeroApostado(requestAltaApuestaDTO.getNumero());
        apuesta.setMontoApostado(requestAltaApuestaDTO.getMontoApostado());

        sorteoEncontrado.agregarApuesta(apuesta);

        NumeroApostado nuevoNumeroApostado = new NumeroApostado();
        nuevoNumeroApostado.setNumero(requestAltaApuestaDTO.getNumero());

        sorteoEncontrado.agregarNumeroApostado(nuevoNumeroApostado);

        sorteoRepository.save(sorteoEncontrado);

        ResponseAltaApuestaDTO responseDTO=new ResponseAltaApuestaDTO();
        responseDTO.setFecha_sorteo(sorteoEncontrado.getFecha());
        responseDTO.setMontoApostado(apuesta.getMontoApostado());
        responseDTO.setId_cliente(apuesta.getId_cliente());
        responseDTO.setNumero(apuesta.getNumeroApostado());
        return responseDTO;



    }
}
