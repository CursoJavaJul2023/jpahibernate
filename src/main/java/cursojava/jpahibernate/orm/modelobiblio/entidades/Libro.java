package cursojava.jpahibernate.orm.modelobiblio.entidades;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "BIBLIO", name = "LIBROS")
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titulo;

	private String isbn;

	private String editorial;
	
	@Column(name = "NUM_PAGINAS")
	private Integer numeroPaginas;

	@Column(name = "FECHA_COMPRA")
	private LocalDate fechaCompra;
	
	@ManyToMany
	@JoinTable(
		schema = "BIBLIO",
		name = "LIBROS_AUTORES",
		joinColumns = @JoinColumn(name = "IDLIBRO", referencedColumnName = "ID"),
		inverseJoinColumns = @JoinColumn(name = "IDAUTOR", referencedColumnName = "ID")
	)
	private Set<Autor> autores = new HashSet<Autor>();
	
	@OneToMany(mappedBy = "libro")
	private Set<Prestamo> prestamos = new HashSet<Prestamo>();
	
	public Libro() {
	}

	public Libro(String titulo) {
		this.titulo = titulo;
	}

	public Libro(String titulo, String isbn, String editorial, Integer numeroPaginas, LocalDate fechaCompra) {
		this.titulo = titulo;
		this.isbn = isbn;
		this.editorial = editorial;
		this.numeroPaginas = numeroPaginas;
		this.fechaCompra = fechaCompra;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public Integer getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(Integer numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Set<Autor> getAutores() {
		return autores;
	}

	public void setAutores(Set<Autor> autores) {
		this.autores = autores;
	}

	public Set<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void setPrestamos(Set<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	@Override
	public String toString() {
		return String.format("Libro [id=%s, titulo=%s, isbn=%s, editorial=%s, numeroPaginas=%s, fechaCompra=%s]", id,
				titulo, isbn, editorial, numeroPaginas, fechaCompra);
	}
	


}






