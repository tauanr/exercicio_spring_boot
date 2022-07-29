package br.com.kyros.exercicio_spring_boot.validation;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponseDto {

	private List<Violation> violations = new ArrayList<>();

	public List<Violation> getViolations() {
		return violations;
	}

	public void setViolations(List<Violation> violations) {
		this.violations = violations;
	}
}
