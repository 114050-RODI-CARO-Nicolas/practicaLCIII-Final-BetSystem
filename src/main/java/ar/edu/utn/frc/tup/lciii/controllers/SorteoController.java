package ar.edu.utn.frc.tup.lciii.controllers;


import ar.edu.utn.frc.tup.lciii.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lciii.dtos.common.SorteoForUpdateDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.SorteoResponseDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.InfoApuestasGanadasDTO;
import ar.edu.utn.frc.tup.lciii.services.SorteoServiceImplementation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/loteria")
public class SorteoController {

    @Autowired
    SorteoServiceImplementation sorteoServiceImplementation;

    @PostMapping("/sorteos")
    public ResponseEntity<SorteoResponseDTO> altaSorteo(){
        SorteoResponseDTO nuevoSorteo = sorteoServiceImplementation.altaSorteo();
        return new ResponseEntity<>(nuevoSorteo, HttpStatus.CREATED);
    }

    @GetMapping("/sorteos")
    public ResponseEntity<List<SorteoResponseDTO>> obtenerTodosSorteos(){

        List<SorteoResponseDTO> responseDTOList = sorteoServiceImplementation.obtenerTodosLosSorteos();
        return new ResponseEntity<>(responseDTOList, HttpStatus.ACCEPTED);
    }

    @GetMapping("/sorteos/{fecha}")
    public ResponseEntity<SorteoResponseDTO> obtenerSorteoPorFecha(@PathVariable LocalDate fecha){

        SorteoResponseDTO responseDTO = sorteoServiceImplementation.obtenerSorteoPorFecha(fecha);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    };

    @DeleteMapping("/sorteos/{fecha}")
    public ResponseEntity<HashMap<String, Boolean>> borrarSorteoPorFecha(@PathVariable LocalDate fecha){

        boolean deletedSuccesfully= sorteoServiceImplementation.borrarSorteoPorFecha(fecha);

        HashMap<String, Boolean> resultHashMap=new HashMap<>();
        resultHashMap.put("Borrado: ", deletedSuccesfully);

        return new ResponseEntity<>(resultHashMap, HttpStatus.ACCEPTED);
    }

    @PutMapping("/sorteos/{fecha}")
    public ResponseEntity<SorteoResponseDTO> modificarSorteoPorFecha(@PathVariable LocalDate fecha, @RequestBody SorteoForUpdateDTO sorteoForUpdateDTO)
    {
        SorteoResponseDTO responseDTO= sorteoServiceImplementation.modificarSorteoPorFecha(fecha, sorteoForUpdateDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);


    }

    @GetMapping("/ganador/{id_sorteo}")
    public ResponseEntity<InfoApuestasGanadasDTO> obtenerInfoApuestasGanadas(@PathVariable long id_sorteo){

        InfoApuestasGanadasDTO responseDTO = sorteoServiceImplementation.obtenerInfoApuestasGanadas(id_sorteo);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorApi handleEntityNotFoundException(EntityNotFoundException ex)
    {
        LocalDateTime localDateTime = LocalDateTime.now();
        ErrorApi errorApi = new ErrorApi();
        errorApi.setTimestamp(String.valueOf(localDateTime));
        errorApi.setMessage(ex.getMessage());
        errorApi.setStatus(HttpStatus.NOT_FOUND.value());
        errorApi.setError(ex.toString());
        return errorApi;
    };




    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorApi handleGeneralException(
            Exception ex
    )
    {
        LocalDateTime localDateTime = LocalDateTime.now();
        ErrorApi errorApi = new ErrorApi();
        errorApi.setTimestamp(String.valueOf(localDateTime));
        errorApi.setMessage(ex.getMessage());
        errorApi.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorApi.setError(ex.toString());
        return errorApi;
    }






}
