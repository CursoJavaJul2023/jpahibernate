package cursojava.jpahibernate.orm.modelocompras.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

public class AuditoriaDeEntidades {
	
	// Podemos tener métodos específicos para cada tipo de entidad
	// o comunes, según necesidades de proyecto
	
//	@PostPersist
//	private void alta(ClienteCompras cte) {
//		
//	}
//	
//	@PostPersist
//	private void alta(Articulo art) {
//		
//	}
	
	@PostPersist
	private void alta(Object entidad) {
		
		System.out.println("Alta de entidad");
		System.out.println(entidad);
		
	}
	
	@PostRemove
	private void baja(Object entidad) {
		
		System.out.println("Baja de entidad");
		System.out.println(entidad);
		
	}
	
	@PostUpdate
	private void cambio(Object entidad) {
		
		System.out.println("Cambio en entidad");
		System.out.println(entidad);
		
	}
	
	

}
