package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.tiburones.daos.ActividadDAO;
import es.uvigo.esei.mei.tiburones.entidades.Actividad;

@Service
public class ActividadServiceImpl implements ActividadService {
	@Autowired
	ActividadDAO actividadDAO;

	@Override
	@Transactional
	public Actividad crear(Actividad ataque) {
		return actividadDAO.save(ataque);
	}

	@Override
	@Transactional
	public Actividad modificar(Actividad ataque) {
		return actividadDAO.save(ataque);
	}

	@Override
	@Transactional
	public void eliminar(Actividad ataque) {
		actividadDAO.delete(ataque);
	}

	@Override
	@Transactional(readOnly = true)
	public Actividad buscarPorId(Long id) {
		return actividadDAO.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Actividad> buscarTodos() {
		return actividadDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Actividad> buscarPorTipo(String patron) {
		return actividadDAO.findByTipoContaining(patron);
	}


}
