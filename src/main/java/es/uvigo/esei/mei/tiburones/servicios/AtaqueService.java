package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import es.uvigo.esei.mei.tiburones.entidades.Actividad;
import es.uvigo.esei.mei.tiburones.entidades.Ataque;
import es.uvigo.esei.mei.tiburones.entidades.Persona;
import es.uvigo.esei.mei.tiburones.entidades.Tiburon;
import es.uvigo.esei.mei.tiburones.entidades.Ubicacion;

public interface AtaqueService {
	public Ataque crear(Ataque lesion);
	public Ataque modificar(Ataque lesion);
	public void eliminar(Ataque lesion);
	public Ataque buscarPorId(Long id);
	public List<Ataque> buscarTodos();
	public List<Ataque> buscarPorFecha(String patron);
	public List<Ataque> buscarPorTiburon(Long idTiburon);
	public List<Ataque> buscarPorActividad(Long idActividad);
	public List<Ataque> buscarPorUbicacion(Long idUbicacion);
	public List<Ataque> buscarPorPersona(Long idPersona);
	
	public List<Tiburon> buscarTiburones();
	public List<Actividad> buscarActividades();
	public List<Ubicacion> buscarUbicaciones();
	public List<Persona> buscarPersonas();
}
