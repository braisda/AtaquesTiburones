package es.uvigo.esei.mei.tiburones.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uvigo.esei.mei.tiburones.daos.AtaqueDAO;
import es.uvigo.esei.mei.tiburones.daos.HospitalDAO;
import es.uvigo.esei.mei.tiburones.daos.LesionDAO;
import es.uvigo.esei.mei.tiburones.entidades.Ataque;
import es.uvigo.esei.mei.tiburones.entidades.Hospital;
import es.uvigo.esei.mei.tiburones.entidades.Investigador;
import es.uvigo.esei.mei.tiburones.entidades.Lesion;
import es.uvigo.esei.mei.tiburones.entidades.Tiburon;

@Service
public class LesionServiceImpl implements LesionService {
	@Autowired
	LesionDAO lesionDAO;

	@Autowired
	AtaqueDAO ataqueDAO;
	
	@Autowired
	HospitalDAO hospitalDAO;

	@Override
	@Transactional
	public Lesion crear(Lesion ataque) {
		return lesionDAO.save(ataque);
	}

	@Override
	@Transactional
	public Lesion modificar(Lesion ataque) {
		return lesionDAO.save(ataque);
	}

	@Override
	@Transactional
	public void eliminar(Lesion ataque) {
		lesionDAO.delete(ataque);
	}

	@Override
	@Transactional(readOnly = true)
	public Lesion buscarPorId(Long id) {
		return lesionDAO.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lesion> buscarTodos() {
		return lesionDAO.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lesion> buscarPorTipo(String patron) {
		return lesionDAO.findByTipoContaining(patron);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Lesion> buscarPorMortal(String patron) {
		return lesionDAO.findByMortalContaining(patron);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lesion> buscarPorDescripcion(String patron) {
		return lesionDAO.findByDescripcionContaining(patron);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Ataque> buscarAtaques() {
		return new ArrayList<Ataque>();
		//return ataqueDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Hospital> buscarHospitales() {
		return hospitalDAO.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Lesion> buscarPorAtaque(Long idAtaque) {
		return lesionDAO.findByAtaque_id(idAtaque);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Lesion> buscarPorHospital(Long idHospital) {
		return lesionDAO.findByHospital_id(idHospital);
	}
}
