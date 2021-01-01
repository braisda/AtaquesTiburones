package es.uvigo.esei.mei.tiburones.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uvigo.esei.mei.tiburones.entidades.Investigador;

@Repository
public interface InvestigadorDAO extends JpaRepository<Investigador, Long>{
	List<Investigador> findByNombreContaining(String patron);
	List<Investigador> findByInstitucionContaining(String patron);
}
