package cursojava.jpahibernate.orm.modelocompras.servicios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import cursojava.jpahibernate.orm.modelobanco.servicios.NegocioException;
import cursojava.jpahibernate.orm.modelocompras.entidades.Articulo;
import cursojava.jpahibernate.orm.modelocompras.entidades.ClienteCompras;
import cursojava.jpahibernate.orm.modelocompras.entidades.Compra;
import cursojava.jpahibernate.orm.modelocompras.entidades.DetalleCompra;
import cursojava.jpahibernate.orm.modelocompras.entidades.Domicilio;
import cursojava.jpahibernate.orm.modelocompras.enums.MedioDePago;
import cursojava.jpahibernate.orm.modelocompras.enums.Subscripcion;

public class OperacionesModeloComprasImpl {
	
	
	public void cargarModeloCompras(int numeroClientes, int numeroArticulos,
			int numeroMaximoArticulosPorCompra) throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelocompras");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			// Creamos los clientes de compras
			
			for(int i = 1; i <= numeroClientes; i++) {
				ClienteCompras cte = new ClienteCompras(
					String.format("%08dB", i),
					"Nombre " + i,
					"Apellidos " + i,
					new Domicilio("C/ de Prueba", 28001, "Madrid", "Madrid"),
					"correo@algo.es",
					"USR" + i,
					"CLAVE",
					LocalDate.of(1950, 10, 1), 
					BigDecimal.ZERO,
					MedioDePago.TARJETA,
					"", 
					i % 2 == 0 ? Subscripcion.NO_ACTIVA : Subscripcion.ACTIVA,
					null, null, null);
				
				em.persist(cte);
			}

			// Creamos los artículos
			
			for(int i = 1; i <= numeroArticulos; i++) {
				Articulo art = new Articulo(
						String.format("ART%06d", i), "Artículo " + i, BigDecimal.valueOf(100.0));
				
				em.persist(art);
			}
			
			// Efectuamos compras
			
			Random rnd = new Random();
			
			List<Articulo> listaArticulos = em.createQuery("select a from Articulo a", Articulo.class).getResultList();
			
			for (ClienteCompras cliente : em.createQuery("select c from ClienteCompras c", ClienteCompras.class).getResultList()) {
				
				int numeroArticulosPorCompra = rnd.nextInt(numeroMaximoArticulosPorCompra);
				
				if(numeroArticulosPorCompra != 0) {
					
					Compra compra = new Compra(
						String.format("%s%06d", cliente.getNif(), System.currentTimeMillis() % 1_000_000),
						cliente,
						LocalDate.now(), BigDecimal.ZERO, null, null, null
					);
					
					em.persist(compra);
					
					BigDecimal totalCompra = BigDecimal.ZERO;
					
					for(int i = 1; i <= numeroArticulosPorCompra; i++) {
						
						Articulo articulo = listaArticulos.get(rnd.nextInt(listaArticulos.size()));
						
						DetalleCompra detalle = new DetalleCompra(compra, articulo, 1);
						
						em.persist(detalle);
						
						totalCompra = totalCompra.add(
							articulo.getPrecioUnidad().multiply(BigDecimal.valueOf(detalle.getCantidad()))
						);
					}
					
					compra.setTotal(totalCompra);					
				}
				
			}			

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();

			if (tx != null) {
				tx.rollback();
			}
		} finally {

			if (em != null) {
				em.close();
			}

			if (emf != null) {
				emf.close();
			}
		}

	}
	
	public void probarRestriccionesEnCamposDeClienteCompras() throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelocompras");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			int i = 10011;
			ClienteCompras cte = new ClienteCompras(
					String.format("%08d", i),
					"Nombre " + i,
					"Apellidos " + i,
					new Domicilio("C/ de Prueba", 28001, "Madrid", "Madrid"),
					"correo@algo.es",
					"USR" + i,
					"CLAVE",
					LocalDate.of(1950, 10, 1), 
					BigDecimal.ZERO,
					MedioDePago.TARJETA,
					"", 
					i % 2 == 0 ? Subscripcion.NO_ACTIVA : Subscripcion.ACTIVA,
					null, null, null
			);
				
			em.persist(cte);			

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();

			if (tx != null) {
				tx.rollback();
			}
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







