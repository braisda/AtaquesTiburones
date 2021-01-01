package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.tiburones.daos.PersonaDAO;
import es.uvigo.esei.mei.tiburones.entidades.Persona;

@Service
public class PersonaServiceImpl implements PersonaService {
	@Autowired
	PersonaDAO personaDAO;


	@Override
	@Transactional
	public Persona crear(Persona ataque) {
		return personaDAO.save(ataque);
	}

	@Override
	@Transactional
	public Persona modificar(Persona ataque) {
		return personaDAO.save(ataque);
	}

	@Override
	@Transactional
	public void eliminar(Persona ataque) {
		personaDAO.delete(ataque);
	}

	@Override
	@Transactional(readOnly = true)
	public Persona buscarPorId(Long id) {
		return personaDAO.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> buscarTodos() {
		return personaDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> buscarPorNombre(String patron) {
		return personaDAO.findByNombreContaining(patron);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Persona> buscarPorPaisNacimiento(String patron) {
		return personaDAO.findByPaisNacimientoContaining(patron);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Persona> buscarPorFechaNacimiento(String patron) {
		return personaDAO.findByFechaNacimientoContaining(patron);
	}
}
