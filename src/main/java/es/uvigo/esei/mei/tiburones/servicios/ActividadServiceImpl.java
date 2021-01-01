package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.tiburones.daos.ActividadDAO;
import es.uvigo.esei.mei.tiburones.entidades.Actividad;
import es.uvigo.esei.mei.tiburones.entidades.Investigador;

@Service
public class ActividadServiceImpl implements ActividadService {
	@Autowired
	ActividadDAO ataqueDAO;


	@Override
	@Transactional
	public Actividad crear(Actividad ataque) {
		return ataqueDAO.save(ataque);
	}

	@Override
	@Transactional
	public Actividad modificar(Actividad ataque) {
		return ataqueDAO.save(ataque);
	}

	@Override
	@Transactional
	public void eliminar(Actividad ataque) {
		ataqueDAO.delete(ataque);
	}

	@Override
	@Transactional(readOnly = true)
	public Actividad buscarPorId(Long id) {
		return ataqueDAO.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Actividad> buscarTodos() {
		return ataqueDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Actividad> buscarPorTipo(String patron) {
		return ataqueDAO.findByTipoContaining(patron);
	}


}
