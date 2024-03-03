package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.domain.Apuesta;
import ar.edu.utn.frc.tup.lciii.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lciii.dtos.common.RequestAltaApuestaDTO;
import ar.edu.utn.frc.tup.lciii.dtos.common.ResponseApuestaDTO;
import ar.edu.utn.frc.tup.lciii.services.SorteoServiceImplementation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("loteria/apuesta")
public class ApuestasController {

    @Autowired
    SorteoServiceImplementation sorteoServiceImplementation;

    @PostMapping
    public ResponseEntity<ResponseApuestaDTO> altaApuesta(@RequestBody RequestAltaApuestaDTO requestAltaApuestaDTO){
        ResponseApuestaDTO responseDTO = sorteoServiceImplementation.altaApuesta(requestAltaApuestaDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);


    };

    @GetMapping
    public ResponseEntity<List<ResponseApuestaDTO>> obtenerTodasLasApuestas()
    {
        List<ResponseApuestaDTO> responseApuestaDTOList = sorteoServiceImplementation.obtenerTodasLasApuestas();
        return new ResponseEntity<>(responseApuestaDTOList, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<ResponseApuestaDTO> obtenerApuestaPorId(Long idApuesta)
    {
        ResponseApuestaDTO responseApuestaDTO = sorteoServiceImplementation.obtenerApuestaPorId(idApuesta);
        return new ResponseEntity<>(responseApuestaDTO, HttpStatus.OK);
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
