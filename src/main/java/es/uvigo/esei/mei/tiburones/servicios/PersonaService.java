package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import es.uvigo.esei.mei.tiburones.entidades.Persona;

public interface PersonaService {
	public Persona crear(Persona persona);
	public Persona modificar(Persona persona);
	public void eliminar(Persona persona);
	public Persona buscarPorId(Long id);
	public List<Persona> buscarTodos();
	public List<Persona> buscarPorNombre(String patron);
	public List<Persona> buscarPorPaisNacimiento(String patron);
	public List<Persona> buscarPorFechaNacimiento(String patron);
	
}
