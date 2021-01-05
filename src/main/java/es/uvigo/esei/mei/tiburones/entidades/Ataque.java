package es.uvigo.esei.mei.tiburones.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Ataque implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fecha")
	private String fecha;
	
	@ManyToOne
	@JoinColumn(name="tiburon_id")
	private Tiburon tiburon;
	
	@ManyToOne
	@JoinColumn(name="actividad_id")
	private Actividad actividad;
	
	@ManyToOne
	@JoinColumn(name="ubicacion_id")
	private Ubicacion ubicacion;
	
	@ManyToOne
	@JoinColumn(name="persona_id")
	private Persona persona;
	
	@OneToMany(mappedBy="ataque", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OrderBy("id asc")
	private List<Lesion> lesiones = new ArrayList<>();
	
	private Tiburon infoTiburon;
	
	public Ataque() {
	}
	
	public Ataque(String fecha, Tiburon tiburon, Actividad actividad, Ubicacion ubicacion, Persona persona,
			List<Lesion> lesiones) {
		super();
		this.fecha = fecha;
		this.tiburon = tiburon;
		this.actividad = actividad;
		this.ubicacion = ubicacion;
		this.persona = persona;
		this.lesiones = lesiones;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public Tiburon getTiburon() {
		return tiburon;
	}

	public void setTiburon(Tiburon tiburon) {
		this.tiburon = tiburon;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Lesion> getLesiones() {
		return lesiones;
	}

	public void setLesiones(List<Lesion> lesiones) {
		this.lesiones = lesiones;
	}

	public Tiburon getInfoTiburon() {
		return infoTiburon;
	}

	public void setInfoTiburon(Tiburon infoTiburon) {
		this.infoTiburon = infoTiburon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Ataque other = (Ataque) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Ataque [id=" + id + ", fecha=" + fecha + "]";
	}
	
}
