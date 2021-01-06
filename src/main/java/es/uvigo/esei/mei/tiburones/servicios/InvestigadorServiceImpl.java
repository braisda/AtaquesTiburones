package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.tiburones.daos.InvestigadorDAO;
import es.uvigo.esei.mei.tiburones.entidades.Investigador;

@Service
public class InvestigadorServiceImpl implements InvestigadorService {
	@Autowired
	InvestigadorDAO investigadorDAO;

	@Override
	@Transactional
	public Investigador crear(Investigador investigador) {
		return investigadorDAO.save(investigador);
	}

	@Override
	@Transactional
	public Investigador modificar(Investigador investigador) {
		return investigadorDAO.save(investigador);
	}

	@Override
	@Transactional
	public void eliminar(Investigador investigador) {
		investigadorDAO.delete(investigador);
	}

	@Override
	@Transactional(readOnly = true)
	public Investigador buscarPorId(Long id) {
		return investigadorDAO.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Investigador> buscarTodos() {
		return investigadorDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Investigador> buscarPorNombre(String patron) {
		return investigadorDAO.findByNombreContaining(patron);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Investigador> buscarPorInstitucion(String patron) {
		return investigadorDAO.findByInstitucionContaining(patron);
	}

}
