package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.tiburones.daos.UbicacionDAO;
import es.uvigo.esei.mei.tiburones.entidades.Ubicacion;
import es.uvigo.esei.mei.tiburones.entidades.Investigador;

@Service
public class UbicacionServiceImpl implements UbicacionService {
	@Autowired
	UbicacionDAO ubicacionDAO;


	@Override
	@Transactional
	public Ubicacion crear(Ubicacion ataque) {
		return ubicacionDAO.save(ataque);
	}

	@Override
	@Transactional
	public Ubicacion modificar(Ubicacion ataque) {
		return ubicacionDAO.save(ataque);
	}

	@Override
	@Transactional
	public void eliminar(Ubicacion ataque) {
		ubicacionDAO.delete(ataque);
	}

	@Override
	@Transactional(readOnly = true)
	public Ubicacion buscarPorId(Long id) {
		return ubicacionDAO.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ubicacion> buscarTodos() {
		return ubicacionDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ubicacion> buscarPorCiudad(String patron) {
		return ubicacionDAO.findByCiudadContaining(patron);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Ubicacion> buscarPorPais(String patron) {
		return ubicacionDAO.findByPaisContaining(patron);
	}


}
