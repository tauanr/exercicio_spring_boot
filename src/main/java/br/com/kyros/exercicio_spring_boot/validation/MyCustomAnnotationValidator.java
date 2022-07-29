package br.com.kyros.exercicio_spring_boot.validation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.kyros.exercicio_spring_boot.model.Competencia;
import br.com.kyros.exercicio_spring_boot.repository.CompetenciaRepository;

public class MyCustomAnnotationValidator implements ConstraintValidator<MyCustomAnnotation, String> {

	@Autowired
	private CompetenciaRepository competenciaRepository;
	
	private String entity;

	@Override
	public void initialize(MyCustomAnnotation myCustomAnnotation) {
		this.entity = myCustomAnnotation.entity();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Optional<Competencia> optional = competenciaRepository.findById(Long.valueOf(value));
		System.out.println(this.entity);
		if (optional.isEmpty()) {
			return false;
		}
		return true;
	}
}
