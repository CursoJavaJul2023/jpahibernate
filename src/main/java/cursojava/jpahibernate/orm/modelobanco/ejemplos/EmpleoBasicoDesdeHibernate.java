package cursojava.jpahibernate.orm.modelobanco.ejemplos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import cursojava.jpahibernate.orm.modelobanco.entidades.Cliente;

public class EmpleoBasicoDesdeHibernate {
	
	public static void main(String[] args) {

		Transaction tx = null;
		
		try (
				StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
																.configure().enableAutoClose()
																.build();
				
				SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
				
				Session session = sf.openSession();
		)
		{
				
			tx = session.beginTransaction();
//			tx = session.getTransaction();
//			tx.begin();
			
			Cliente cte = new Cliente("22222222A", "Primero", "de Prueba");
			
			Cliente clientePrevio = session.get(Cliente.class, cte.getNif());
			if(clientePrevio != null) {
				// Borrar
				session.delete(clientePrevio);
			}
			
			// Alta
			session.save(cte);
			
			// Búsqueda por PK
			Cliente elCliente = session.get(Cliente.class, cte.getNif());
			if(elCliente != null) {
				System.out.println(elCliente);
			}
			
			// Si el cliente existe
			
			elCliente.setNombre("Nombre cambiado");
			elCliente.setApellidos("Apellidos cambiados");
			
			// session.saveOrUpdate(elCliente);
			
			tx.commit();			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			
			if(tx != null) {
				tx.rollback();
			}
		}
		
	}

}
