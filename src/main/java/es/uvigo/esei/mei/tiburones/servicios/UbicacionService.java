package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import es.uvigo.esei.mei.tiburones.entidades.Ubicacion;

public interface UbicacionService {
	public Ubicacion crear(Ubicacion ubicacion);
	public Ubicacion modificar(Ubicacion ubicacion);
	public void eliminar(Ubicacion ubicacion);
	public Ubicacion buscarPorId(Long id);
	public List<Ubicacion> buscarTodos();
	public List<Ubicacion> buscarPorCiudad(String patron);
	public List<Ubicacion> buscarPorPais(String patron);
	
}
