package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import es.uvigo.esei.mei.tiburones.entidades.Actividad;

public interface ActividadService {
	public Actividad crear(Actividad actividad);
	public Actividad modificar(Actividad actividad);
	public void eliminar(Actividad actividad);
	public Actividad buscarPorId(Long id);
	public List<Actividad> buscarTodos();
	public List<Actividad> buscarPorTipo(String patron);
	
}
