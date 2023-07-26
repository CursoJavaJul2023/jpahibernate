package cursojava.jpahibernate.orm.modelobiblio.entidades;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "BIBLIO", name = "LIBROS")
public class Prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "IDLIBRO", referencedColumnName = "ID")
	private Libro libro;

	@ManyToOne
	@JoinColumn(name = "NIFUSUARIO", referencedColumnName = "NIF")
	private Usuario usuario;

	@Column(name = "FECHA_INICIO")
	private LocalDate fechaInicio;
	
	@Column(name = "FECHA_FIN")
	private LocalDate fechaFin;
	
	@Column(name = "FECHA_ENTREGA")
	private LocalDate fechaEntrega;
	
	public Prestamo() {
	}

	public Prestamo(Libro libro, Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin) {
		this.libro = libro;
		this.usuario = usuario;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Prestamo(Libro libro, Usuario usuario, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaEntrega) {
		this.libro = libro;
		this.usuario = usuario;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaEntrega = fechaEntrega;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	@Override
	public String toString() {
		return String.format("Prestamo [id=%s, libro=%s, usuario=%s, fechaInicio=%s, fechaFin=%s, fechaEntrega=%s]", id,
				libro, usuario, fechaInicio, fechaFin, fechaEntrega);
	}
	
	
	
}