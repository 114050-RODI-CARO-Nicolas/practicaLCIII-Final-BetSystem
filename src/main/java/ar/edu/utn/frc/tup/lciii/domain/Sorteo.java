package ar.edu.utn.frc.tup.lciii.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class Sorteo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private int totalEnReserva;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "sorteo")
    private List<NumeroApostado> numerosApostados = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "sorteo")
    private List<Apuesta> apuestas = new ArrayList<>();

    @Column(nullable = true)
    private int numeroGanadorSecreto;


    public void agregarNumeroApostado(NumeroApostado numeroApostado) {
        numerosApostados.add(numeroApostado);
        numeroApostado.setSorteo(this);
    }

    public void quitarNumeroApostado(NumeroApostado numeroApostado) {
       numerosApostados.remove(numeroApostado);
       numeroApostado.setSorteo(null);
    }

    public void agregarApuesta(Apuesta apuesta){
        apuestas.add(apuesta);
        apuesta.setSorteo(this);
    }

    public void quitarApuesta(Apuesta apuesta)
    {
        apuestas.remove(apuesta);
        apuesta.setSorteo(null);
    }




}
