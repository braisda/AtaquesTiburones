package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import es.uvigo.esei.mei.tiburones.entidades.Investigador;

public interface InvestigadorService {
	public Investigador crear(Investigador investigador);
	public Investigador modificar(Investigador investigador);
	public void eliminar(Investigador investigador);
	public Investigador buscarPorId(Long id);
	public List<Investigador> buscarTodos();
	public List<Investigador> buscarPorNombre(String patron);
	public List<Investigador> buscarPorInstitucion(String patron);
	//public List<Investigador> buscarPorTiburon(Long idTiburon);
	
}
