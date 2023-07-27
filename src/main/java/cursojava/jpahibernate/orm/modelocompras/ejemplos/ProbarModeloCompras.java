package cursojava.jpahibernate.orm.modelocompras.ejemplos;

import cursojava.jpahibernate.orm.modelobanco.servicios.NegocioException;
import cursojava.jpahibernate.orm.modelocompras.servicios.OperacionesModeloComprasImpl;

public class ProbarModeloCompras {
	
	public static void main(String[] args) {
		
		OperacionesModeloComprasImpl opCompras = new OperacionesModeloComprasImpl();
		
		try {
			// opCompras.cargarModeloCompras(100, 1000, 2);
			
			opCompras.probarRestriccionesEnCamposDeClienteCompras();
			
		} catch (NegocioException e) {
			
			e.printStackTrace();
		}
	}

}
