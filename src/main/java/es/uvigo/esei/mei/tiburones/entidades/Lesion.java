package es.uvigo.esei.mei.tiburones.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import es.uvigo.esei.mei.tiburones.enumerados.TipoLesion;

@Entity
public class Lesion implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "mortal")
	private Boolean mortal;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="ataque_id")
	private Ataque ataque;
	
	@ManyToOne
	@JoinColumn(name="hospital_id")
	private Hospital hospital;
	
	public Lesion() {
	}
	
	public Lesion(String tipo, Boolean mortal, String descripcion, Ataque ataque, Hospital hospital) {
		this.tipo = tipo;
		this.mortal = mortal;
		this.descripcion = descripcion;
		this.ataque = ataque;
		this.hospital = hospital;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Boolean getMortal() {
		return mortal;
	}
	
	public void setMortal(Boolean mortal) {
		this.mortal = mortal;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Ataque getAtaque() {
		return ataque;
	}

	public void setAtaque(Ataque ataque) {
		this.ataque = ataque;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mortal == null) ? 0 : mortal.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Lesion other = (Lesion) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mortal == null) {
			if (other.mortal != null)
				return false;
		} else if (!mortal.equals(other.mortal))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Lesion [id=" + id + ", tipo=" + tipo + ", mortal=" + mortal + ", descripcion=" + descripcion + "]";
	}
	
}
