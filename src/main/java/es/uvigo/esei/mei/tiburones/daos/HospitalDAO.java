package es.uvigo.esei.mei.tiburones.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uvigo.esei.mei.tiburones.entidades.Hospital;
import es.uvigo.esei.mei.tiburones.entidades.Tiburon;
import es.uvigo.esei.mei.tiburones.entidades.Ubicacion;

@Repository
public interface HospitalDAO extends JpaRepository<Hospital, Long>{
	List<Hospital> findByNombreContaining(String patron);
	List<Hospital> findByCiudadContaining(String patron);
	List<Hospital> findByPaisContaining(String patron);
}
