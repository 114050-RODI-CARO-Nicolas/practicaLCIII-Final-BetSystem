package ar.edu.utn.frc.tup.lciii.services;


import ar.edu.utn.frc.tup.lciii.domain.Apuesta;
import ar.edu.utn.frc.tup.lciii.domain.GetSorteoResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.NuevoSorteoResponseDTO;
import ar.edu.utn.frc.tup.lciii.domain.NumeroApostado;
import ar.edu.utn.frc.tup.lciii.domain.Sorteo;
import ar.edu.utn.frc.tup.lciii.dtos.common.NuevoSorteoResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.RequestAltaApuestaDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseAltaApuestaDTO;
import ar.edu.utn.frc.tup.lciii.repositories.ApuestaRepository;
import ar.edu.utn.frc.tup.lciii.repositories.NumeroRepository;
import ar.edu.utn.frc.tup.lciii.repositories.SorteoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
    public NuevoSorteoResponseDTO altaSorteo() {
        Sorteo nuevoSorteo=new Sorteo();
        nuevoSorteo.setFecha(localDate);
        nuevoSorteo.setNumeroGanadorSecreto(generateRandomFiveDigitInt());
        sorteoRepository.save(nuevoSorteo);


        NuevoSorteoResponseDTO responseDTO = new NuevoSorteoResponseDTO();
        responseDTO.setFecha(nuevoSorteo.getFecha());
        responseDTO.setId(nuevoSorteo.getId());
        responseDTO.setTotalEnReserva(nuevoSorteo.getTotalEnReserva());
        return responseDTO;
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
        sorteoEncontrado.setTotalEnReserva(sorteoEncontrado.getTotalEnReserva()+ apuesta.getMontoApostado());

        sorteoRepository.save(sorteoEncontrado);

        ResponseAltaApuestaDTO responseDTO=new ResponseAltaApuestaDTO();
        responseDTO.setFecha_sorteo(sorteoEncontrado.getFecha());
        responseDTO.setMontoApostado(apuesta.getMontoApostado());
        responseDTO.setId_cliente(apuesta.getId_cliente());
        responseDTO.setNumero(apuesta.getNumeroApostado());
        return responseDTO;

    }

    @Override
    public List<GetSorteoResponseDTO> obtenerTodosLosSorteos() {

      List<Sorteo> sorteos=sorteoRepository.findAll();
      List<GetSorteoResponseDTO> responseDTOList = new ArrayList<>();

        for (Sorteo sorteo:sorteos
             ) {
            GetSorteoResponseDTO responseDTO = new GetSorteoResponseDTO();
            responseDTO.setFecha(sorteo.getFecha());
            responseDTO.setTotalEnReserva(sorteo.getTotalEnReserva());

            List<NumeroApostado> numeroApostadoList = sorteo.getNumerosApostados();

            for (NumeroApostado numeroApostado:numeroApostadoList
                 ) {

                HashMap<Long, Integer> numeroApostadoHashmap = new HashMap<>();
                numeroApostadoHashmap.put(numeroApostado.getId(), numeroApostado.getNumero());
                responseDTO.agregarNumeroSorteado(numeroApostadoHashmap);
            }

            responseDTOList.add(responseDTO);

        }
        return responseDTOList;
    };

    public GetSorteoResponseDTO obtenerSorteoPorFecha(LocalDate fecha){

        Sorteo sorteoEncontrado = sorteoRepository.findByFecha(fecha);
        GetSorteoResponseDTO responseDTO = new GetSorteoResponseDTO();
        responseDTO.setFecha(sorteoEncontrado.getFecha());
        responseDTO.setTotalEnReserva(sorteoEncontrado.getTotalEnReserva());

        List<NumeroApostado> numeroApostadoList = sorteoEncontrado.getNumerosApostados();

        for (NumeroApostado numeroApostado:numeroApostadoList
        ) {

            HashMap<Long, Integer> numeroApostadoHashmap = new HashMap<>();
            numeroApostadoHashmap.put(numeroApostado.getId(), numeroApostado.getNumero());
            responseDTO.agregarNumeroSorteado(numeroApostadoHashmap);
        }
        return responseDTO;


    }

    //UTILS

    public int generateRandomFiveDigitInt(){

        int min=10000;
        int max=99999;

        Random random = new Random();
        int randomInt=random.nextInt( max-min+1   ) + min;
        return randomInt;
    }





}


