package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import es.uvigo.esei.mei.tiburones.entidades.Hospital;

public interface HospitalService {
	public Hospital crear(Hospital hospital);
	public Hospital modificar(Hospital hospital);
	public void eliminar(Hospital hospital);
	public Hospital buscarPorId(Long id);
	public List<Hospital> buscarTodos();
	public List<Hospital> buscarPorNombre(String patron);
	public List<Hospital> buscarPorCiudad(String patron);
	public List<Hospital> buscarPorPais(String patron);
	
}
