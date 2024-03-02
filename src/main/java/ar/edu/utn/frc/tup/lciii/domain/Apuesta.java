package ar.edu.utn.frc.tup.lciii.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Apuesta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String id_cliente;

    private Integer numeroApostado;
    private Integer montoApostado;


   // @Column(nullable = true)
    private Integer porcentajeGanancia;

   // @Column(nullable = true)
    private Integer montoGanado;


    @JoinColumn(name = "id_sorteo", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Sorteo sorteo;



    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(! (o instanceof Apuesta)) return false;
        return id != null && id.equals(( (Apuesta) o) .getId());
    }


}
