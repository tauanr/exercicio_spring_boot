package br.com.kyros.exercicio_spring_boot.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.kyros.exercicio_spring_boot.repository.DepartamentoRepository;
import br.com.kyros.exercicio_spring_boot.repository.FuncionarioRepository;

public class FieldMustBeUniqueInDbAnnotationValidator
		implements ConstraintValidator<FieldMustBeUniqueInDbAnnotation, String> {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	private String uniqueField;

	@Override
	public void initialize(FieldMustBeUniqueInDbAnnotation fieldMustBeUniqueInDbAnnotation) {
		this.uniqueField = fieldMustBeUniqueInDbAnnotation.uniqueField();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		switch (uniqueField) {
		case "numeroDeRegistro":
			if(!value.matches("[0-9]{1,}")) {
				context.disableDefaultConstraintViolation();
		        context
		            .buildConstraintViolationWithTemplate("Incorrect Format for a unique value")
		            .addConstraintViolation();
		        return false;
			}
			if (funcionarioRepository.findByNumeroDeRegistro(Integer.valueOf(value)).isPresent()) {
				return false;
			}
			return true;
		case "cpf":
			if (funcionarioRepository.findByCpf(value).isPresent()) {
				return false;
			}
			return true;
		case "codigoDaFolhaDePagamento":
			if(!value.matches("[0-9]{1,}")) {
				context.disableDefaultConstraintViolation();
		        context
		            .buildConstraintViolationWithTemplate("Incorrect Format for a unique value")
		            .addConstraintViolation();
		        return false;
			}
			if (departamentoRepository.findByCodigoDaFolhaDePagamento(Integer.valueOf(value)).isPresent()) {
				return false;
			}
			return true;
		default:
			return false;
		}
	}
}
