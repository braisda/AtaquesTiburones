package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.tiburones.daos.HospitalDAO;
import es.uvigo.esei.mei.tiburones.daos.UbicacionDAO;
import es.uvigo.esei.mei.tiburones.entidades.Ubicacion;
import es.uvigo.esei.mei.tiburones.entidades.Hospital;
import es.uvigo.esei.mei.tiburones.entidades.Investigador;

@Service
public class HospitalServiceImpl implements HospitalService {
	@Autowired
	HospitalDAO hospitalDAO;


	@Override
	@Transactional
	public Hospital crear(Hospital ataque) {
		return hospitalDAO.save(ataque);
	}

	@Override
	@Transactional
	public Hospital modificar(Hospital ataque) {
		return hospitalDAO.save(ataque);
	}

	@Override
	@Transactional
	public void eliminar(Hospital ataque) {
		hospitalDAO.delete(ataque);
	}

	@Override
	@Transactional(readOnly = true)
	public Hospital buscarPorId(Long id) {
		return hospitalDAO.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Hospital> buscarTodos() {
		return hospitalDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Hospital> buscarPorNombre(String patron) {
		return hospitalDAO.findByNombreContaining(patron);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Hospital> buscarPorCiudad(String patron) {
		return hospitalDAO.findByCiudadContaining(patron);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Hospital> buscarPorPais(String patron) {
		return hospitalDAO.findByPaisContaining(patron);
	}


}
