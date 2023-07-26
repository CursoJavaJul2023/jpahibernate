package cursojava.jpahibernate.orm.modelocompras.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(schema = "DEMOS", name = "CLIENTES")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCompras {

	@Id
	@EqualsAndHashCode.Include
	@NonNull
	private String nif;

	@NonNull
	private String nombre;

	@NonNull
	private String apellidos;

	private String domicilio;

	@Column(name = "CP")
	private Integer codigoPostal;	

	private String localidad;

	private String provincia;
	
	@NonNull
	private String email;
	
	@NonNull
	private String usuario;
	
	@NonNull
	private String clave;

	private LocalDate fechaNacimiento;
	
	private BigDecimal descuento;
	
	private String medioDePago;
	
	private String comentarios;
	
	private Character subscripcion;

	@Column(insertable = false, updatable = false)
	private Integer numero;
	
	@Column(insertable = false, updatable = false)
	private LocalDateTime fechaAlta;
	
	@OneToMany(mappedBy = "cliente")
	@ToString.Exclude
	private Set<Compra> compras;
}





