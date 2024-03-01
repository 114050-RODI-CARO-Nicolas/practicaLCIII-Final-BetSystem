package ar.edu.utn.frc.tup.lciii.controllers;


import ar.edu.utn.frc.tup.lciii.domain.GetSorteoResponseDTO;
import ar.edu.utn.frc.tup.lciii.domain.Sorteo;
import ar.edu.utn.frc.tup.lciii.dtos.common.NuevoSorteoResponseDTO;
import ar.edu.utn.frc.tup.lciii.services.SorteoServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sorteos")
public class SorteoController {

    @Autowired
    SorteoServiceImplementation sorteoServiceImplementation;

    @PostMapping("/")
    public ResponseEntity<NuevoSorteoResponseDTO> altaSorteo(){
        NuevoSorteoResponseDTO nuevoSorteo = sorteoServiceImplementation.altaSorteo();
        return new ResponseEntity<>(nuevoSorteo, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<GetSorteoResponseDTO>> obtenerTodosSorteos(){

        List<GetSorteoResponseDTO> responseDTOList = sorteoServiceImplementation.obtenerTodosLosSorteos();
        return new ResponseEntity<>(responseDTOList, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{fecha}")
    public ResponseEntity<GetSorteoResponseDTO> obtenerSorteoPorFecha(@PathVariable LocalDate fecha){

        GetSorteoResponseDTO responseDTO = sorteoServiceImplementation.obtenerSorteoPorFecha(fecha);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }



}
