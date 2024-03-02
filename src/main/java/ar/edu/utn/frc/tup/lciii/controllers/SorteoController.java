package ar.edu.utn.frc.tup.lciii.controllers;


import ar.edu.utn.frc.tup.lciii.dtos.common.GetSorteoResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.InfoApuestasGanadasDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.NuevoSorteoResponseDTO;
import ar.edu.utn.frc.tup.lciii.services.SorteoServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/loteria")
public class SorteoController {

    @Autowired
    SorteoServiceImplementation sorteoServiceImplementation;

    @PostMapping("/sorteos")
    public ResponseEntity<NuevoSorteoResponseDTO> altaSorteo(){
        NuevoSorteoResponseDTO nuevoSorteo = sorteoServiceImplementation.altaSorteo();
        return new ResponseEntity<>(nuevoSorteo, HttpStatus.CREATED);
    }

    @GetMapping("/sorteos")
    public ResponseEntity<List<GetSorteoResponseDTO>> obtenerTodosSorteos(){

        List<GetSorteoResponseDTO> responseDTOList = sorteoServiceImplementation.obtenerTodosLosSorteos();
        return new ResponseEntity<>(responseDTOList, HttpStatus.ACCEPTED);
    }

    @GetMapping("/sorteos/{fecha}")
    public ResponseEntity<GetSorteoResponseDTO> obtenerSorteoPorFecha(@PathVariable LocalDate fecha){

        GetSorteoResponseDTO responseDTO = sorteoServiceImplementation.obtenerSorteoPorFecha(fecha);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/loteria/ganador/{id_sorteo}")
    public ResponseEntity<InfoApuestasGanadasDTO> obtenerInfoApuestasGanadas(@PathVariable long id_sorteo){

        InfoApuestasGanadasDTO responseDTO = sorteoServiceImplementation.obtenerInfoApuestasGanadas(id_sorteo);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }





}
