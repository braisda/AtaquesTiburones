package es.uvigo.esei.mei.tiburones.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uvigo.esei.mei.tiburones.entidades.Tiburon;

@Repository
public interface TiburonDAO extends JpaRepository<Tiburon, Long>{
	List<Tiburon> findByRazaContaining(String patron);
	List<Tiburon> findByInvestigadores_id(Long investigadores_id);
}
