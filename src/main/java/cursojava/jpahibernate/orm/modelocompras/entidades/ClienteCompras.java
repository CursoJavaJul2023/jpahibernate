package cursojava.jpahibernate.orm.modelocompras.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import cursojava.jpahibernate.constraints.Nif;
import cursojava.jpahibernate.orm.modelocompras.conversores.ConversorDeSubcripcion;
import cursojava.jpahibernate.orm.modelocompras.enums.MedioDePago;
import cursojava.jpahibernate.orm.modelocompras.enums.Subscripcion;
import cursojava.jpahibernate.orm.modelocompras.listener.AuditoriaDeEntidades;
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
@EntityListeners( AuditoriaDeEntidades.class )
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
	@Nif
	private String nif;

	@NonNull
	//@NotBlank
	@Size(min = 3)
	private String nombre;

	@NonNull
	@Pattern(regexp = "^[A-Za-z ñÑ]{3,40}$", message = "Apellidos con caracteres o longitud incorrecta")	
	private String apellidos;

	@Embedded
	@NotNull
	private Domicilio domicilio;

	@NonNull
	//@Email
	private String email;
	
	@NonNull
	private String usuario;
	
	@NonNull
	private String clave;

	@Past
	private LocalDate fechaNacimiento;
	
	private BigDecimal descuento;
	
	@Enumerated(EnumType.STRING)
	private MedioDePago medioDePago;
	
	private String comentarios;
	
	@Convert(converter = ConversorDeSubcripcion.class)
	private Subscripcion subscripcion;

	@Column(insertable = false, updatable = false)
	private Integer numero;
	
	@Column(insertable = false, updatable = false)
	private LocalDateTime fechaAlta;
	
	@OneToMany(mappedBy = "cliente")
	@ToString.Exclude
	private Set<Compra> compras;
	
	//////////////////////////////////////////////////////////
	// Métodos callback
	
	@PrePersist
	private void antesDeInsertar() {
		
		System.out.println("Antes de insertar ClienteCompras "  + nif);
	}
	
	@PostPersist
	private void despuesDeInsertar() {
		
		System.out.println("Después de insertar ClienteCompras " + nif);
	}
	
	
}





