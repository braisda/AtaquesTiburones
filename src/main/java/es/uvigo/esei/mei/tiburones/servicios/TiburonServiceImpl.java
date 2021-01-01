package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.tiburones.daos.InvestigadorDAO;
import es.uvigo.esei.mei.tiburones.daos.TiburonDAO;
import es.uvigo.esei.mei.tiburones.entidades.Investigador;
import es.uvigo.esei.mei.tiburones.entidades.Tiburon;

@Service
public class TiburonServiceImpl implements TiburonService {
	@Autowired
	TiburonDAO tiburonDAO;
	
	@Autowired
	InvestigadorDAO investigadorDAO;

	@Override
	@Transactional
	public Tiburon crear(Tiburon tiburon) {
		return tiburonDAO.save(tiburon);
	}

	@Override
	@Transactional
	public Tiburon modificar(Tiburon tiburon) {
		return tiburonDAO.save(tiburon);
	}

	@Override
	@Transactional
	public void eliminar(Tiburon tiburon) {
		tiburonDAO.delete(tiburon);
	}

	@Override
	@Transactional(readOnly = true)
	public Tiburon buscarPorId(Long id) {
		return tiburonDAO.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tiburon> buscarTodos() {
		return tiburonDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tiburon> buscarPorRaza(String patron) {
		return tiburonDAO.findByRazaContaining(patron);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tiburon> buscarPorInvestigador(Long idInvestigador) {
		return tiburonDAO.findByInvestigadores_id(idInvestigador);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Investigador> buscarInvestigadores() {
		return investigadorDAO.findAll();
	}

}
