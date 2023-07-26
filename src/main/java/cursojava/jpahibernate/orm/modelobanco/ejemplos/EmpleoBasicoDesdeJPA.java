package cursojava.jpahibernate.orm.modelobanco.ejemplos;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import cursojava.jpahibernate.orm.modelobanco.dto.DatosInforme;
import cursojava.jpahibernate.orm.modelobanco.dto.EstadisticasCuenta;
import cursojava.jpahibernate.orm.modelobanco.entidades.Cliente;
import cursojava.jpahibernate.orm.modelobanco.entidades.Cuenta;
import cursojava.jpahibernate.orm.modelobanco.servicios.NegocioException;
import cursojava.jpahibernate.orm.modelobanco.servicios.OperacionesModeloBancoImpl;

public class EmpleoBasicoDesdeJPA {
	
	public static void main(String[] args) {
		
		// empleoBasicoEntidades();
		
		try {
			OperacionesModeloBancoImpl opBanco = new OperacionesModeloBancoImpl();
			
			// opBanco.crearClientes(1, 1000);	
			// opBanco.crearCuentas(10, 10, 10);
			// opBanco.asignarCuentasAClientes(4);
			
			// opBanco.incrementarSaldosEnCuentas(BigDecimal.valueOf(10.0), new BigDecimal("10.0"));
			
			// opBanco.buscarClientesPorNombreYApellidos("%10", "%20").forEach(System.out::println);
			
//			opBanco.buscarClientesPorAltaEntreFechas(Date.valueOf("2023-07-01"), Date.valueOf("2023-07-31"))
//			       .forEach(System.out::println);
			
//			for (Cliente cte : opBanco.buscarClientesPorRangoDeSaldos(BigDecimal.valueOf(100.0), BigDecimal.valueOf(200.0))) {
//				System.out.println(cte);
//				for (Cuenta cta : cte.getCuentas()) {
//					System.out.println("\t" + cta);
//				}
//			}
			
//			for (Object matrizConLosCampos : opBanco.leerDatosClientes()) {
//				Object[] datos = (Object[]) matrizConLosCampos;
//				System.out.printf("NIF: %s - %s %s%n", datos[0], datos[1], datos[2]);				
//			}
			
//			for (Object dato : opBanco.consultarEstadisticasCuentas()) {
//				EstadisticasCuenta estadisticas = (EstadisticasCuenta) dato;
//				System.out.println(estadisticas);				
//			}
			
			for (DatosInforme datos : opBanco.realizarInformeSucursales()) {
				System.out.println(datos);
			}
				
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
				
	}

	public static void empleoBasicoEntidades() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			emf = Persistence.createEntityManagerFactory("modelobanco");
			em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			Cliente cte = new Cliente("11111111A", "Primero", "de Prueba");			
			
			Cliente clientePrevio = em.find(Cliente.class, cte.getNif());
			if(clientePrevio != null) {
				// Borrar
				em.remove(clientePrevio);
			}
			
			// Alta
			em.persist(cte);
			
			// Búsqueda por PK
			Cliente elCliente = em.find(Cliente.class, cte.getNif());
			if(elCliente != null) {
				System.out.println(elCliente);
			}
			
			// Suponemos que el cliente existe
			
			// Al modificar un cliente obtenido por el em al acabar la tx
			// se envían los cambios a la BBDD
			elCliente.setNombre("Nombre cambiado");
			elCliente.setCiudad("Madrid");			
			
			tx.commit();			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			if(tx != null) {
				tx.rollback();
			}
		}
		finally {
			
			if(emf != null) {
				emf.close();
			}
			
			if(em != null) {
				em.close();
			}
		}
	}

}
