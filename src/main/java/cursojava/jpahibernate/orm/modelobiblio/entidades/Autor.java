package cursojava.jpahibernate.orm.modelobiblio.entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(schema = "BIBLIO", name = "AUTORES")
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	private String apellidos;

	private String nacionalidad;

	@Column(name = "FECHA_NACIMIENTO")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@ManyToMany(mappedBy = "autores")
	private Set<Libro> libros = new HashSet<Libro>();
	
	public Autor() {
	}

	public Autor(String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
		
	public Autor(String nombre, String apellidos, String nacionalidad, Date fechaNacimiento) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Set<Libro> getLibros() {
		return libros;
	}

	public void setLibros(Set<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public String toString() {
		return String.format("Autor [id=%s, nombre=%s, apellidos=%s, nacionalidad=%s, fechaNacimiento=%s]", id, nombre,
				apellidos, nacionalidad, fechaNacimiento);
	}
	
//	private Libro[] a;
//	private List<Libro> b;
//	private Set<Libro> c;
//	private Map<Integer, Libro> d;


}
