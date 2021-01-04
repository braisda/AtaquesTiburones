package es.uvigo.esei.mei.tiburones.servicios;

import java.util.List;

import es.uvigo.esei.mei.tiburones.entidades.Ataque;
import es.uvigo.esei.mei.tiburones.entidades.Hospital;
import es.uvigo.esei.mei.tiburones.entidades.Lesion;

public interface LesionService {
	public Lesion crear(Lesion lesion);
	public Lesion modificar(Lesion lesion);
	public void eliminar(Lesion lesion);
	public Lesion buscarPorId(Long id);
	public List<Lesion> buscarTodos();
	public List<Lesion> buscarPorTipo(String patron);
	public List<Lesion> buscarPorMortal(String patron);
	public List<Lesion> buscarPorDescripcion(String patron);
	public List<Lesion> buscarPorAtaque(Long idAtaque);
	public List<Lesion> buscarPorHospital(Long idHospital);
	public List<Ataque> buscarAtaques();
	public List<Hospital> buscarHospitales();
}
