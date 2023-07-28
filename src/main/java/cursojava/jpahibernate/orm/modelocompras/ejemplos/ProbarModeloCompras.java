package cursojava.jpahibernate.orm.modelocompras.ejemplos;

import java.math.BigDecimal;

import cursojava.jpahibernate.orm.modelocompras.servicios.ConsultasModeloComprasImpl;
import cursojava.jpahibernate.orm.modelocompras.servicios.OperacionesModeloComprasImpl;

public class ProbarModeloCompras {
	
	public static void main(String[] args) {
		
		OperacionesModeloComprasImpl opCompras = new OperacionesModeloComprasImpl();
		
		ConsultasModeloComprasImpl consultas = new ConsultasModeloComprasImpl();
		
		try {
			// opCompras.cargarModeloCompras(100, 1000, 2);
			
			// opCompras.probarRestriccionesEnCamposDeClienteCompras();
			
//			consultas.consultarClientesPorEmail("correo@algo.es")
//				.forEach(System.out::println);
			
//			ClienteCompras cte = consultas.consultarClientesPorUsuarioYClave("USR10", "CLAVE");
//			if(cte != null) {
//				System.out.println(cte);
//			}
			
//			consultas.consultarClientesPorCompraEntreFechas(LocalDate.of(2023, 7, 1), LocalDate.of(2023, 7, 31))
//				.forEach(System.out::println);

			
//			consultas.consultarClientesPorCodigoDeArticulo("ART000108")
//				.forEach(System.out::println);
			
			
//			consultas.consultarComprasPorArticuloConPrecioEnRango(BigDecimal.valueOf(0), BigDecimal.valueOf(100))
//				.forEach(System.out::println);
			
			consultas.consultarComprasPorArticuloConPrecioEnRango2(BigDecimal.valueOf(0), BigDecimal.valueOf(100))
			.forEach(System.out::println);
			
//			for (Object matriz : consultas.consultarComprasPorArticuloConPrecioEnRango2(BigDecimal.valueOf(0), BigDecimal.valueOf(100))) {
//				
//				Object[] campos = (Object[]) matriz;
//				for (Object campo : campos) {
//					System.out.printf("%s-%s%n", campo, campo.getClass());
//				}
//				
//				System.out.println("----------------------------------------------------");
//			}
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}

