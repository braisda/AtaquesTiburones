package es.uvigo.esei.mei.tiburones.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uvigo.esei.mei.tiburones.entidades.Tiburon;
import es.uvigo.esei.mei.tiburones.entidades.Ubicacion;

@Repository
public interface UbicacionDAO extends JpaRepository<Ubicacion, Long>{
	List<Ubicacion> findByCiudadContaining(String patron);
	List<Ubicacion> findByPaisContaining(String patron);
}
