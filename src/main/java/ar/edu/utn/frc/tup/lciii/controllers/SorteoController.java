package ar.edu.utn.frc.tup.lciii.controllers;


import ar.edu.utn.frc.tup.lciii.domain.Sorteo;
import ar.edu.utn.frc.tup.lciii.services.SorteoServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sorteos")
public class SorteoController {

    @Autowired
    SorteoServiceImplementation sorteoServiceImplementation;

    @PostMapping("/")
    public ResponseEntity<Sorteo> altaSorteo(){
        Sorteo nuevoSorteo = sorteoServiceImplementation.altaSorteo();
        return new ResponseEntity<>(nuevoSorteo, HttpStatus.CREATED);
    }



}
