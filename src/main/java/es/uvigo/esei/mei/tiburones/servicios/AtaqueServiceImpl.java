package es.uvigo.esei.mei.tiburones.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.tiburones.daos.ActividadDAO;
import es.uvigo.esei.mei.tiburones.daos.AtaqueDAO;
import es.uvigo.esei.mei.tiburones.daos.PersonaDAO;
import es.uvigo.esei.mei.tiburones.daos.TiburonDAO;
import es.uvigo.esei.mei.tiburones.daos.UbicacionDAO;
import es.uvigo.esei.mei.tiburones.entidades.Actividad;
import es.uvigo.esei.mei.tiburones.entidades.Ataque;
import es.uvigo.esei.mei.tiburones.entidades.Persona;
import es.uvigo.esei.mei.tiburones.entidades.Tiburon;
import es.uvigo.esei.mei.tiburones.entidades.Ubicacion;

@Service
public class AtaqueServiceImpl implements AtaqueService {
	@Autowired
	AtaqueDAO ataqueDAO;

	@Autowired
	TiburonDAO tiburonDAO;
	
	@Autowired
	ActividadDAO actividadDAO;
	
	@Autowired
	UbicacionDAO ubicacionDAO;
	
	@Autowired
	PersonaDAO personaDAO;

	@Override
	@Transactional
	public Ataque crear(Ataque ataque) {
		return ataqueDAO.save(ataque);
	}

	@Override
	@Transactional
	public Ataque modificar(Ataque ataque) {
		return ataqueDAO.save(ataque);
	}

	@Override
	@Transactional
	public void eliminar(Ataque ataque) {
		ataqueDAO.delete(ataque);
	}

	@Override
	@Transactional(readOnly = true)
	public Ataque buscarPorId(Long id) {
		return ataqueDAO.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ataque> buscarTodos() {
		return ataqueDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ataque> buscarPorFecha(String patron) {
		return ataqueDAO.findByFechaContaining(patron);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Tiburon> buscarTiburones() {
		return tiburonDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Actividad> buscarActividades() {
		return actividadDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Ubicacion> buscarUbicaciones() {
		return ubicacionDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Persona> buscarPersonas() {
		return personaDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Ataque> buscarPorTiburon(Long idTiburon) {
		return ataqueDAO.findByTiburon_id(idTiburon);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Ataque> buscarPorActividad(Long idActividad) {
		return ataqueDAO.findByActividad_id(idActividad);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Ataque> buscarPorUbicacion(Long idUbicacion) {
		return ataqueDAO.findByUbicacion_id(idUbicacion);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Ataque> buscarPorPersona(Long idPersona) {
		return ataqueDAO.findByPersona_id(idPersona);
	}
}
