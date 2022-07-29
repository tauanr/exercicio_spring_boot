package br.com.kyros.exercicio_spring_boot.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.kyros.exercicio_spring_boot.repository.CompetenciaRepository;
import br.com.kyros.exercicio_spring_boot.repository.DepartamentoRepository;
import br.com.kyros.exercicio_spring_boot.repository.FeedbackRepository;
import br.com.kyros.exercicio_spring_boot.repository.FuncionarioRepository;

public class IdExistsAnnotationValidator implements ConstraintValidator<IdExistsAnnotation, String> {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private CompetenciaRepository competenciaRepository;

	@Autowired
	private FeedbackRepository feedbackRepository;

	private String entity;

	@Override
	public void initialize(IdExistsAnnotation idExistsAnnotation) {
		this.entity = idExistsAnnotation.entity();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (!value.matches("[0-9]+")) {
			return false;
		}
		switch (entity) {
		case "Funcionario":
			if (funcionarioRepository.findById(Long.valueOf(value)).isEmpty()) {
				return false;
			}
			return true;
		case "Departamento":
			if (departamentoRepository.findById(Long.valueOf(value)).isEmpty()) {
				return false;
			}
			return true;
		case "Feedback":
			if (feedbackRepository.findById(Long.valueOf(value)).isEmpty()) {
				return false;
			}
			return true;
		case "Competencia":
			if (competenciaRepository.findById(Long.valueOf(value)).isEmpty()) {
				return false;
			}
			return true;
		default:
			return false;
		}
	}

}
