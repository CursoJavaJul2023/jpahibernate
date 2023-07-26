package cursojava.jpahibernate.orm.modelobanco.servicios;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import cursojava.jpahibernate.orm.modelobanco.dto.DatosInforme;
import cursojava.jpahibernate.orm.modelobanco.dto.EstadisticasCuenta;
import cursojava.jpahibernate.orm.modelobanco.entidades.Cliente;
import cursojava.jpahibernate.orm.modelobanco.entidades.Cuenta;
import cursojava.jpahibernate.orm.modelobanco.entidades.CuentaPk;
import cursojava.jpahibernate.orm.modelobanco.entidades.Movimiento;

public class OperacionesModeloBancoImpl {
	
	
	public void crearClientes(int desde, int numero) throws NegocioException {
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			emf = Persistence.createEntityManagerFactory("modelobanco");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			for(int i = desde; i <= desde + numero; i++) {
				
				Cliente cte = new Cliente(
					String.format("%08dA", i),
					"Nombre " + i,
					"Apellidos " + i
				);
				
				// No tiene porque enviarse el SQL de manera inmediata
				// Las sentencias SQL se va a enviar: acaba la transacción, se ejecuta una consulta en la interviene
				// el mismo tipo de entidad
				em.persist(cte);
				
				// Forzar el envío de SQL pendiente a la bbdd
				// em.flush();
				
				// Permite liberar las referencias a las entidades que están en el contexto persistencia
				// em.clear();
				
				if(i % 1000 == 0) {
					em.flush();
					em.clear();
				}				
				
			}			
			
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			if(tx != null) {
				tx.rollback();
			}
		}
		finally {
			
			if(em != null) {
				em.close();
			}
			
			if(emf != null) {
				emf.close();
			}
		}
		
	}
	
	
	public void crearCuentas(int numeroBancos, int numeroSucursales, int numeroCuentas) throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelobanco");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			Random rnd = new Random();

			for(int i = 1; i <= numeroBancos; i++) {
				for(int j = 1; j <= numeroSucursales; j++) {
					for(int k = 1; k <= numeroCuentas; k++) {
						
						Cuenta cta = new Cuenta(
							new CuentaPk(
								String.format("%04d", i),
								String.format("%04d", j),
								String.format("%010d", k)
							),
							BigDecimal.valueOf( rnd.nextDouble() * 1000.0)
						);
						
						em.persist(cta);
						
						Movimiento mov = new Movimiento(cta, "Alta de la cuenta");
						
						em.persist(mov);
					}
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
	
	
	public void asignarCuentasAClientes(int numeroMaximoDeCuentas) throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelobanco");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			Random rnd = new Random();

			// Query consulta = em.createQuery("select cte from Cliente cte");
			// consulta.getSingleResult();
			// consulta.getResultList();
			// consulta.getResultStream();
			
			TypedQuery<Cliente> consulta = em.createQuery("select cte from Cliente cte join fetch cte.cuentas cta", Cliente.class);
			List<Cliente> losClientes = consulta.getResultList();
			
			List<Cuenta> lasCuentas = em.createQuery("select cta from Cuenta cta", Cuenta.class)
			  .getResultList();
			
			for(Cliente cliente : losClientes) {
				
				int numeroCuentas = rnd.nextInt(numeroMaximoDeCuentas);
				
				for(int i = 0; i <= numeroCuentas; i++) {
					
					Cuenta cuenta = lasCuentas.get(rnd.nextInt(lasCuentas.size()));
					cliente.getCuentas().add(cuenta);					
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
	
	public void incrementarSaldosEnCuentas(BigDecimal cantidad, BigDecimal saldoMinimo) throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelobanco");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			em.createQuery("update Cuenta cta set cta.saldo = cta.saldo + ?1 where cta.saldo <= ?2")
			  .setParameter(1, cantidad)
			  .setParameter(2, saldoMinimo)
			  .executeUpdate();

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
	
	public List<Cliente> buscarClientesPorNombreYApellidos(String nombre, String apellidos) throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelobanco");
			em = emf.createEntityManager();

// 			return em.createQuery("select cte from Cliente cte where cte.nombre like ?1 or cte.apellidos like ?2", Cliente.class)
			
			return em.createNamedQuery("Cliente.buscarClientesPorNombreYApellidos", Cliente.class)
			  .setParameter(1, nombre)
			  .setParameter(2, apellidos)
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
	
	public List<Cliente> buscarClientesPorAltaEntreFechas(Date inicio, Date fin) throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelobanco");
			em = emf.createEntityManager();

			return em.createNamedQuery("Cliente.buscarClientesPorAltaEntreFechas", Cliente.class)
			  .setParameter("fechaInicio", inicio)
			  .setParameter("fechaFin", fin)
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
	
	public List<Cliente> buscarClientesPorRangoDeSaldos(BigDecimal minimo, BigDecimal maximo) throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelobanco");
			em = emf.createEntityManager();
			
			List<Cliente> resultado = em.createNamedQuery("Cliente.buscarPorSaldoEnRango", Cliente.class)
			   .setParameter("min", minimo)
			   .setParameter("max", maximo)
			   .getResultList();
			 			 
			return resultado;
			
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
	
	public List leerDatosClientes() throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelobanco");
			em = emf.createEntityManager();
			
			return em.createNamedQuery("Cliente.leerDatosClientes")
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
	
	public List consultarEstadisticasCuentas() throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelobanco");
			em = emf.createEntityManager();
			
			return em.createNativeQuery(
				"select banco, sucursal, count(*) as numero, sum(saldo) as suma, stddev(saldo) as std, variance(saldo) as var " +
						"from modelobanco.cuentas " +
						"group by banco, sucursal order by banco, sucursal",
				EstadisticasCuenta.class					
			).getResultList();

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
	
	public List<DatosInforme> realizarInformeSucursales() throws NegocioException {

		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			emf = Persistence.createEntityManagerFactory("modelobanco");
			em = emf.createEntityManager();
			
			return em.createNamedQuery("Cuentas.informeDeSucursalesPorSaldos", DatosInforme.class)
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















