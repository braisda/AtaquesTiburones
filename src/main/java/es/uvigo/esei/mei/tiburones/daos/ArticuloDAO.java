package es.uvigo.esei.mei.tiburones.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.uvigo.esei.mei.tiburones.entidades.Articulo;

@Repository
public interface ArticuloDAO extends JpaRepository<Articulo, Long>{
	List<Articulo> findByDescripcionContaining(String patron);
	List<Articulo> findByFamiliaId(Long familiaId);
}
