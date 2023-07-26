package cursojava.jpahibernate.orm.modelobiblio.entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(schema = "BIBLIO", name = "USUARIOS")
public class Usuario {
	
	@Id
	private String nif;

	private String nombre;

	private String apellidos;

	private String domicilio;

	private String ciudad;

	private String provincia;
	
	private String correo;

	@Column(name = "FECHA_NACIMIENTO")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	@Column(name = "FECHA_ALTA", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;
	
	@OneToMany(mappedBy = "usuario")
	private Set<Prestamo> prestamos = new HashSet<Prestamo>();	
	
	public Usuario() {
	}

	public Usuario(String nif, String nombre, String apellidos) {
		this.nif = nif;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public Usuario(String nif, String nombre, String apellidos, String domicilio, String ciudad, String provincia,
			String correo, Date fechaNacimiento) {
		this.nif = nif;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
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

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return String.format(
				"Usuario [nif=%s, nombre=%s, apellidos=%s, domicilio=%s, ciudad=%s, provincia=%s, correo=%s, fechaNacimiento=%s, fechaAlta=%s]",
				nif, nombre, apellidos, domicilio, ciudad, provincia, correo, fechaNacimiento, fechaAlta);
	}
	
	
	

}
