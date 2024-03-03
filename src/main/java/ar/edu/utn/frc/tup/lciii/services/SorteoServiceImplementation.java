package ar.edu.utn.frc.tup.lciii.services;


import ar.edu.utn.frc.tup.lciii.domain.Apuesta;
import ar.edu.utn.frc.tup.lciii.dtos.common.SorteoResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.*;
import ar.edu.utn.frc.tup.lciii.domain.NumeroApostado;
import ar.edu.utn.frc.tup.lciii.domain.Sorteo;
import ar.edu.utn.frc.tup.lciii.repositories.ApuestaRepository;
import ar.edu.utn.frc.tup.lciii.repositories.NumeroRepository;
import ar.edu.utn.frc.tup.lciii.repositories.SorteoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
    public SorteoResponseDTO altaSorteo() {
        Sorteo nuevoSorteo=new Sorteo();
        nuevoSorteo.setFecha(localDate);
        nuevoSorteo.setNumeroGanadorSecreto(generateRandomFiveDigitInt());
        nuevoSorteo.setTotalEnReserva(0);
        sorteoRepository.save(nuevoSorteo);


        SorteoResponseDTO responseDTO = new SorteoResponseDTO();
        responseDTO.setId(nuevoSorteo.getId());
        responseDTO.setFecha(nuevoSorteo.getFecha());
        responseDTO.setTotalEnReserva(nuevoSorteo.getTotalEnReserva());
        return responseDTO;
    };

    @Override
    public ResponseApuestaDTO altaApuesta(RequestAltaApuestaDTO requestAltaApuestaDTO) {

        try {
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



            ResponseApuestaDTO responseDTO=new ResponseApuestaDTO();
            responseDTO.setFecha_sorteo(sorteoEncontrado.getFecha());
            responseDTO.setMontoApostado(apuesta.getMontoApostado());
            responseDTO.setId_cliente(apuesta.getId_cliente());
            responseDTO.setNumero(apuesta.getNumeroApostado());

            switch (compararCifras(sorteoEncontrado.getNumeroGanadorSecreto(), apuesta.getNumeroApostado())) {

                case 2:
                    apuesta.setPorcentajeGanancia(700);
                    break;
                case 3:
                    apuesta.setPorcentajeGanancia(7000);
                    break;
                case 4:
                    apuesta.setPorcentajeGanancia(60000);
                    break;
                case 5:
                    apuesta.setPorcentajeGanancia(350000);
                    break;
                default:
                    apuesta.setPorcentajeGanancia(0);

            }


            if(compararCifras(sorteoEncontrado.getNumeroGanadorSecreto(), apuesta.getNumeroApostado()) >= 2 ) {
                apuesta.setMontoGanado(this.obtenerGanancia(apuesta.getMontoApostado(), apuesta.getPorcentajeGanancia()));
                responseDTO.setResultado("GANADOR");
            } else {
                apuesta.setMontoGanado(0);
                responseDTO.setResultado("PIERDE");
            }

            if(apuesta.getMontoGanado()>0) {
                apuesta.setMontoGanado(this.obtenerGanancia(apuesta.getMontoApostado(), apuesta.getPorcentajeGanancia()));
                sorteoEncontrado.setTotalEnReserva(sorteoEncontrado.getTotalEnReserva() - apuesta.getMontoGanado());
            }

            sorteoRepository.save(sorteoEncontrado);

            return responseDTO;



        }
        catch (Exception ex) {

            throw ex;

        }

    }

    @Override
    public List<ResponseApuestaDTO> obtenerTodasLasApuestas()
    {
        List<Apuesta> apuestas = apuestaRepository.findAll();
        List<ResponseApuestaDTO> responseDTOList = new ArrayList<>();

        for (Apuesta apuesta: apuestas)
        {
            ResponseApuestaDTO responseDTO = new ResponseApuestaDTO();
            responseDTO.setFecha_sorteo(apuesta.getSorteo().getFecha());
            responseDTO.setId_cliente(apuesta.getId_cliente());
            responseDTO.setNumero(apuesta.getNumeroApostado());
            responseDTO.setMontoApostado(apuesta.getMontoApostado());

            if(compararCifras(apuesta.getSorteo().getNumeroGanadorSecreto(), apuesta.getNumeroApostado()) >= 2 )
            {
                responseDTO.setResultado("GANADOR");
            } else {
                responseDTO.setResultado("Pierde...");
            }
            responseDTOList.add(responseDTO);



        }

        return responseDTOList;


    };

    @Override
    public ResponseApuestaDTO obtenerApuestaPorId(Long idApuesta)
    {
        Apuesta apuestaEncontrada = apuestaRepository.findById(idApuesta).orElseThrow( ()->  new EntityNotFoundException("Source: SorteoServiceImp.obtenerApuestaPorId() "));

        ResponseApuestaDTO responseDTO = new ResponseApuestaDTO();
        responseDTO.setFecha_sorteo(apuestaEncontrada.getSorteo().getFecha());
        responseDTO.setId_cliente(apuestaEncontrada.getId_cliente());
        responseDTO.setNumero(apuestaEncontrada.getNumeroApostado());
        responseDTO.setMontoApostado(apuestaEncontrada.getMontoApostado());

        if(compararCifras(apuestaEncontrada.getSorteo().getNumeroGanadorSecreto(), apuestaEncontrada.getNumeroApostado()) >= 2 )
        {
            responseDTO.setResultado("GANADOR");
        } else {
            responseDTO.setResultado("Pierde...");
        }
        return responseDTO;
    }



    @Override
    public List<SorteoResponseDTO> obtenerTodosLosSorteos() {

      List<Sorteo> sorteos=sorteoRepository.findAll();
      List<SorteoResponseDTO> responseDTOList = new ArrayList<>();

        for (Sorteo sorteo:sorteos
             ) {
            SorteoResponseDTO responseDTO = new SorteoResponseDTO();
            responseDTO.setId(sorteo.getId());
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
    }

    @Override
    public InfoApuestasGanadasDTO obtenerInfoApuestasGanadas(long idSorteo) {

        Sorteo sorteoEncontrado = sorteoRepository.findById(idSorteo).orElseThrow( ()-> new EntityNotFoundException("Source: SorteoServiceImp - obtenerInfoApuestasGanadas"));
        List<Apuesta> apuestasDelSorteo = sorteoEncontrado.getApuestas();

        InfoApuestasGanadasDTO responseDTO = new InfoApuestasGanadasDTO();

        responseDTO.setId_sorteo(sorteoEncontrado.getId());
        responseDTO.setFecha_sorteo(sorteoEncontrado.getFecha());
        responseDTO.setTotalApuestas(apuestasDelSorteo.size());

        int totalPagado=0;

        for (Apuesta apuestaDelSorteo: apuestasDelSorteo
             ) {

            totalPagado += apuestaDelSorteo.getMontoGanado();

        }
        responseDTO.setTotalPagado(totalPagado);
        responseDTO.setTotalReserva(sorteoEncontrado.getTotalEnReserva());

        return responseDTO;

    }



    public SorteoResponseDTO obtenerSorteoPorFecha(LocalDate fecha){

        Sorteo sorteoEncontrado = sorteoRepository.findByFecha(fecha);
        SorteoResponseDTO responseDTO = new SorteoResponseDTO();
        responseDTO.setId(sorteoEncontrado.getId());
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

    @Override
    public SorteoResponseDTO modificarSorteoPorFecha(LocalDate fecha_sorteo, SorteoForUpdateDTO sorteoForUpdateDTO)
    {
        Sorteo sorteoEncontrado = sorteoRepository.findByFecha(fecha_sorteo);
            if(sorteoEncontrado==null){
                throw new EntityNotFoundException("Source: SorteoServiceImp - modificarSorteoPorFecha()");
            };
            if(sorteoForUpdateDTO.getFecha()!=null){
                sorteoEncontrado.setFecha(sorteoForUpdateDTO.getFecha());
            };
            sorteoRepository.save(sorteoEncontrado);

            SorteoResponseDTO responseDTO = new SorteoResponseDTO();
            responseDTO.setId(sorteoEncontrado.getId());
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

    @Override
    public boolean borrarSorteoPorFecha(LocalDate fecha)
    {

        boolean deletedSuccesfully=false;

        try{
            Sorteo sorteoEncontrado = sorteoRepository.findByFecha(fecha);
            if(sorteoEncontrado==null){
                throw new EntityNotFoundException("Source: SorteoServiceImp - borrarSorteoPorFecha()");
            };
            sorteoRepository.delete(sorteoEncontrado);


            deletedSuccesfully=true;
        }
        catch (EntityNotFoundException ex){
            throw ex;
        }
        catch (Exception ex){
            throw ex;
        }


        return deletedSuccesfully;

    };







    //UTILS

    public int obtenerGanancia(int montoApostado, int porcentajeGanancia)
    {
        //Solamente devolver el porcentajeGanancia del montoAPostado

        int resultado;
        resultado = ( (porcentajeGanancia / 100) * montoApostado );
        return resultado;

    }

    public int generateRandomFiveDigitInt()
    {

        Random random = new Random();
        Set<Integer> uniqueDigits = new HashSet<>();

        // Generar dígitos únicos
        while (uniqueDigits.size() < 5) {
            int digit = random.nextInt(10);  // Genera un dígito aleatorio entre 0 y 9
            uniqueDigits.add(digit);
        }

        // Construir el número final a partir de los dígitos únicos
        int result = 0;
        int multiplier = 1;

        for (int digit : uniqueDigits) {
            result += digit * multiplier;
            multiplier *= 10;
        }

        return result;

    }

    public static int compararCifras(int referencia, int aComparar)
    {

        String cadenaReferencia = String.valueOf(referencia);
        String cadenaComparar = String.valueOf(aComparar);

        int cantidadCoincidencias = 0;

        for (int i = 0; i < cadenaReferencia.length(); i++) {
            char cifraReferencia = cadenaReferencia.charAt(i);

            if(cadenaComparar.contains(String.valueOf(cifraReferencia))) {
                cantidadCoincidencias++;
                cadenaComparar=cadenaComparar.replaceFirst(String.valueOf(cifraReferencia), "");

            }
        }
        return cantidadCoincidencias;
    }





}


