package es.uvigo.esei.mei.tiburones.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uvigo.esei.mei.tiburones.entidades.Ataque;

@Repository
public interface AtaqueDAO extends JpaRepository<Ataque, Long>{
	List<Ataque> findByFechaContaining(String patron);
	List<Ataque> findByTiburon_id(Long tiburon_id);
	List<Ataque> findByActividad_id(Long actividad_id);
	List<Ataque> findByUbicacion_id(Long ubicacion_id);
	List<Ataque> findByPersona_id(Long persona_id);
}
