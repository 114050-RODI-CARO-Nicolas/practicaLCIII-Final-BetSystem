package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.domain.Sorteo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SorteoRepository extends JpaRepository<Sorteo, Long> {

    Sorteo findByFecha(LocalDate fecha);
}
