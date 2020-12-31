package es.uvigo.esei.mei.tiburones.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Investigador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "institucion")
	private Boolean instiucion;
	
	@ManyToMany
	private List<Tiburon> tiburones = new ArrayList<>();;

	public Investigador() {
	}

	public Investigador(String nombre, Boolean instiucion) {
		this.nombre = nombre;
		this.instiucion = instiucion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getInstiucion() {
		return instiucion;
	}

	public void setInstiucion(Boolean instiucion) {
		this.instiucion = instiucion;
	}

	public Collection<Tiburon> getTiburones() {
		return tiburones;
	}

	public void setTiburones(List<Tiburon> tiburones) {
		this.tiburones = tiburones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((instiucion == null) ? 0 : instiucion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Investigador other = (Investigador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (instiucion == null) {
			if (other.instiucion != null)
				return false;
		} else if (!instiucion.equals(other.instiucion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Investigador [id=" + id + ", nombre=" + nombre + ", instiucion=" + instiucion + "]";
	}

}
