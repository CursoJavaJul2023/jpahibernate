package cursojava.jpahibernate.orm.modelocompras.servicios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import cursojava.jpahibernate.orm.modelobanco.servicios.NegocioException;
import cursojava.jpahibernate.orm.modelocompras.dto.DatosCompra;
import cursojava.jpahibernate.orm.modelocompras.entidades.ClienteCompras;
import cursojava.jpahibernate.orm.modelocompras.entidades.Compra;

public class ConsultasModeloComprasImpl {
	
	public List<ClienteCompras> consultarClientesPorEmail(String email) throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelocompras");
			em = emf.createEntityManager();
			
//			return em.createQuery("select c from ClienteCompras c where c.email = :correo", ClienteCompras.class)
			return em.createNamedQuery("ClienteCompras.porCorreo", ClienteCompras.class)
			  .setParameter("correo", email)
			  .getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			
			return null;

		} finally {

			if (em != null) {
				em.close();
			}

			if (emf != null) {
				emf.close();
			}
		}

	}
	
	public ClienteCompras consultarClientesPorUsuarioYClave(String usuario, String clave) throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelocompras");
			em = emf.createEntityManager();
			
			return em.createNamedQuery("ClienteCompras.porUsuarioyClave", ClienteCompras.class)
				.setParameter("usuario", usuario)
				.setParameter("clave", clave)
				.getSingleResult();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;

		} finally {

			if (em != null) {
				em.close();
			}

			if (emf != null) {
				emf.close();
			}
		}

	}

	public List<ClienteCompras> consultarClientesPorCompraEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelocompras");
			em = emf.createEntityManager();
			
			return em.createNamedQuery("ClienteCompras.porCompraEnRangeDeFechas", ClienteCompras.class)
				.setParameter("inicio", fechaInicio)
				.setParameter("fin", fechaFin)
				.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;

		} finally {

			if (em != null) {
				em.close();
			}

			if (emf != null) {
				emf.close();
			}
		}

	}
	
	public List<ClienteCompras> consultarClientesPorCodigoDeArticulo(String codigoArticulo) throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelocompras");
			em = emf.createEntityManager();
			
			return em.createNamedQuery("ClienteCompras.porArticuloComprado", ClienteCompras.class)
				.setParameter("codigoArticulo", codigoArticulo)
				.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;

		} finally {

			if (em != null) {
				em.close();
			}

			if (emf != null) {
				emf.close();
			}
		}

	}
	
	public List<Compra> consultarComprasPorArticuloConPrecioEnRango(BigDecimal precioMinimo, BigDecimal precioMaximo) throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelocompras");
			em = emf.createEntityManager();
			
			return em.createNamedQuery("Compra.porArticuloEnRangoDePrecio", Compra.class)
				.setParameter("precioMinimo", precioMinimo)
				.setParameter("precioMaximo", precioMaximo)
				.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;

		} finally {

			if (em != null) {
				em.close();
			}

			if (emf != null) {
				emf.close();
			}
		}
	}
		
	public List<DatosCompra> consultarComprasPorArticuloConPrecioEnRango2(BigDecimal precioMinimo, BigDecimal precioMaximo) throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelocompras");
			em = emf.createEntityManager();
			
			return em.createNamedQuery("Compra.porArticuloEnRangoDePrecio2", DatosCompra.class)
				.setParameter("precioMinimo", precioMinimo)
				.setParameter("precioMaximo", precioMaximo)
				.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return null;

		} finally {

			if (em != null) {
				em.close();
			}

			if (emf != null) {
				emf.close();
			}
		}
		

	}
	
	

}

	
	
	
	
	
	