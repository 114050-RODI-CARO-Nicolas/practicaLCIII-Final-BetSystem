package ar.edu.utn.frc.tup.lciii.services;


import ar.edu.utn.frc.tup.lciii.domain.Sorteo;
import ar.edu.utn.frc.tup.lciii.dtos.common.NuevoSorteoResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.RequestAltaApuestaDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseAltaApuestaDTO;
import ar.edu.utn.frc.tup.lciii.repositories.ApuestaRepository;
import ar.edu.utn.frc.tup.lciii.repositories.NumeroRepository;
import ar.edu.utn.frc.tup.lciii.repositories.SorteoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class SorteoServiceTest {

    @Mock
    private SorteoRepository sorteoRepository;

    @Mock
    private ApuestaRepository apuestaRepository;

    @Mock
    private NumeroRepository numeroRepository;


    @InjectMocks
    private SorteoServiceImplementation sorteoServiceImplementation;

    private LocalDate fecha;

    @Test
    public void chequearSiNuevoSorteoRetornaDTOrespuesta()
    {

        NuevoSorteoResponseDTO mockResponseDTO = sorteoServiceImplementation.altaSorteo();
        assertNotNull(mockResponseDTO);



    }

    @Test
    public void chequearAltaApuestaNormal()
    {

        RequestAltaApuestaDTO requestDTO = new RequestAltaApuestaDTO();
        requestDTO.setFecha_sorteo(fecha.now());
        requestDTO.setId_cliente("Tester Gambler");
        requestDTO.setNumero(73158);
        requestDTO.setMontoApostado(100);

        Sorteo mockSorteoEncontrado = new Sorteo();
        mockSorteoEncontrado.setId(1L);
        mockSorteoEncontrado.setFecha(requestDTO.getFecha_sorteo());
        mockSorteoEncontrado.setTotalEnReserva(0);
        mockSorteoEncontrado.setNumeroGanadorSecreto(sorteoServiceImplementation.generateRandomFiveDigitInt());

        when(sorteoRepository.findByFecha(any(LocalDate.class))).thenReturn(mockSorteoEncontrado);

        ResponseAltaApuestaDTO responseDTO = sorteoServiceImplementation.altaApuesta(requestDTO);

        assertNotNull(responseDTO);
        assertEquals(responseDTO.getMontoApostado(), 100);

    };

    @Test
    public void chequearInfoGanadores()
    {
        RequestAltaApuestaDTO mockApuesta1DTO = new RequestAltaApuestaDTO();
            mockApuesta1DTO.setFecha_sorteo(fecha.now());
            mockApuesta1DTO.setId_cliente("Test1 Gambler");
            mockApuesta1DTO.setNumero(73156); //acierta 4 cifras 3156
            mockApuesta1DTO.setMontoApostado(100);

        RequestAltaApuestaDTO mockApuesta2DTO = new RequestAltaApuestaDTO();
            mockApuesta2DTO.setFecha_sorteo(fecha.now());
            mockApuesta2DTO.setId_cliente("Test2 Gambler");
            mockApuesta2DTO.setNumero(84156);        // acierta 3 cifras 156
            mockApuesta1DTO.setMontoApostado(100);

        RequestAltaApuestaDTO mockApuesta3DTO = new RequestAltaApuestaDTO();
            mockApuesta3DTO.setFecha_sorteo(fecha.now());
            mockApuesta3DTO.setId_cliente("Test3 Gambler");
            mockApuesta1DTO.setNumero(90456); // acierta 2 cifras
            mockApuesta3DTO.setMontoApostado(100);

        RequestAltaApuestaDTO mockApuesta4DTO = new RequestAltaApuestaDTO();
            mockApuesta4DTO.setFecha_sorteo(fecha.now());
            mockApuesta4DTO.setId_cliente("Test4 Gambler");
            mockApuesta4DTO.setNumero(20478); // acierta 0 cifras
            mockApuesta4DTO.setMontoApostado(100); //








    }





}
