package br.com.kyros.exercicio_spring_boot.controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.kyros.exercicio_spring_boot.model.Competencia;
import br.com.kyros.exercicio_spring_boot.model.Feedback;
import br.com.kyros.exercicio_spring_boot.model.TipoCompetencia;
import br.com.kyros.exercicio_spring_boot.repository.CompetenciaRepository;
import br.com.kyros.exercicio_spring_boot.repository.FeedbackRepository;
import br.com.kyros.exercicio_spring_boot.validation.IdExistsAnnotation;

public class CompetenciaUpdateForm extends CompetenciaForm {

	@NotBlank
	@Pattern(regexp = "[0-9]{1,18}")
	@IdExistsAnnotation(entity = "Competencia")
	private String id;

	@Override
	public Competencia toCompetencia(CompetenciaRepository competenciaRepository,
			FeedbackRepository feedbackRepository) {
		Optional<Competencia> competenciaOtional = competenciaRepository.findById(Long.valueOf(id));

		if (competenciaOtional.isEmpty()) {
			return null;
		}

		Competencia competencia = competenciaOtional.get();

		List<Feedback> feedbackList = new ArrayList<>();
		for (String idFeedback : super.idFeedbacks) {
			Optional<Feedback> feedback = feedbackRepository.findById(Long.valueOf(idFeedback));

			if (feedback.isEmpty()) {
				return null;
			}
			feedbackList.add(feedback.get());
		}

		competencia.setNome(nome);
		competencia.setConceituacao(conceituacao);
		competencia.setTipo(TipoCompetencia.valueOf(tipo));
		competencia.setFeedbacks(feedbackList);

		competencia.getFeedbacks().forEach(f -> f.setCompetencia(competencia));

		return competencia;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
