package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.RequestAltaApuestaDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseAltaApuestaDTO;
import ar.edu.utn.frc.tup.lciii.services.SorteoServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apuesta")
public class ApuestasController {

    @Autowired
    SorteoServiceImplementation sorteoServiceImplementation;

    @PostMapping
    public ResponseEntity<ResponseAltaApuestaDTO> altaApuesta(@RequestBody RequestAltaApuestaDTO requestAltaApuestaDTO){
        ResponseAltaApuestaDTO responseDTO = sorteoServiceImplementation.altaApuesta(requestAltaApuestaDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);


    }


}
