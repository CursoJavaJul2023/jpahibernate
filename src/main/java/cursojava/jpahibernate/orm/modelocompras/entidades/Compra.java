package cursojava.jpahibernate.orm.modelocompras.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(schema = "DEMOS", name = "COMPRAS")
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Compra {

	@Id
	@EqualsAndHashCode.Include
	@NonNull
	private String codigo;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "CLIENTE", referencedColumnName = "NIF")
	private ClienteCompras cliente;

	@NonNull
	private LocalDate fecha;
	
	private BigDecimal total;
	
	@Column(insertable = false, updatable = false)
	private Integer numero;
	
	@Column(insertable = false, updatable = false)
	private LocalDateTime fechaAlta;
	
	@OneToMany(mappedBy = "compra")
	@ToString.Exclude
	private Set<DetalleCompra> detalle;
}
