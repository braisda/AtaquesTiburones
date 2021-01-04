package es.uvigo.esei.mei.tiburones.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uvigo.esei.mei.tiburones.entidades.Lesion;
import es.uvigo.esei.mei.tiburones.entidades.Persona;
import es.uvigo.esei.mei.tiburones.entidades.Tiburon;

@Repository
public interface LesionDAO extends JpaRepository<Lesion, Long>{
	List<Lesion> findByTipoContaining(String patron);
	List<Lesion> findByMortalContaining(String patron);
	List<Lesion> findByDescripcionContaining(String patron);
	List<Lesion> findByAtaque_id(Long ataque_id);
	List<Lesion> findByHospital_id(Long hospital_id);
}
