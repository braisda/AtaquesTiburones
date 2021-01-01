package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import es.uvigo.esei.mei.tiburones.entidades.Investigador;
import es.uvigo.esei.mei.tiburones.entidades.Tiburon;

public interface TiburonService {
	public Tiburon crear(Tiburon articulo);
	public Tiburon modificar(Tiburon articulo);
	public void eliminar(Tiburon articulo);
	public Tiburon buscarPorId(Long id);
	public List<Tiburon> buscarTodos();
	public List<Tiburon> buscarPorRaza(String patron);
	public List<Tiburon> buscarPorInvestigador(Long idInvestigador);
	public List<Investigador> buscarInvestigadores();
	
}
