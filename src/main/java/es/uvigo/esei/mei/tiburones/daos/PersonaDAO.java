package es.uvigo.esei.mei.tiburones.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uvigo.esei.mei.tiburones.entidades.Persona;

@Repository
public interface PersonaDAO extends JpaRepository<Persona, Long>{
	List<Persona> findByNombreContaining(String patron);
	List<Persona> findByPaisNacimientoContaining(String patron);
	List<Persona> findByFechaNacimientoContaining(String patron);
}
