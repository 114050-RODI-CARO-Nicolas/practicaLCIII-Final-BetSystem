package ar.edu.utn.frc.tup.lciii.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class NumeroApostado implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numero;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sorteo", referencedColumnName = "id")
    private Sorteo sorteo;

}
