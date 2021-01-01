package es.uvigo.esei.mei.tiburones.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uvigo.esei.mei.tiburones.entidades.Actividad;

@Repository
public interface ActividadDAO extends JpaRepository<Actividad, Long>{
	List<Actividad> findByTipoContaining(String patron);
}
