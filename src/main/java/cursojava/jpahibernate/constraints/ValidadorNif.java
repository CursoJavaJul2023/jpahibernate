package cursojava.jpahibernate.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidadorNif implements ConstraintValidator<Nif, String> {
	
	@Override
	public void initialize(Nif constraintAnnotation) {
	
		// Desde aquí accedemos a propiedades de la anotación
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value != null && !value.trim().equals("") && value.matches("^\\d{8}[A-Z]$")) {
			
			return true;
		}
		
		return false;
		
	}

}
