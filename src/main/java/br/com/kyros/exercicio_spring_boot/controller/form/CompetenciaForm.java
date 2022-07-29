package br.com.kyros.exercicio_spring_boot.controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Pattern;

import br.com.kyros.exercicio_spring_boot.model.Competencia;
import br.com.kyros.exercicio_spring_boot.model.Feedback;
import br.com.kyros.exercicio_spring_boot.model.TipoCompetencia;
import br.com.kyros.exercicio_spring_boot.repository.CompetenciaRepository;
import br.com.kyros.exercicio_spring_boot.repository.FeedbackRepository;
import br.com.kyros.exercicio_spring_boot.validation.IdExistsAnnotation;

public class CompetenciaForm {

	@Pattern(regexp = "[a-zA-Z ]{4,20}")
	protected String nome;

	@Pattern(regexp = "[a-zA-Z ]{4,50}")
	protected String conceituacao;

	@Pattern(regexp = "(LIDERANCA)|(ORGANIZACIONAL)|(OPERACIONAL)")
	protected String tipo;

	protected List<@Pattern(regexp = "[0-9]{1,18}") @IdExistsAnnotation(entity = "Feedback") String> idFeedbacks;

	public Competencia toCompetencia(CompetenciaRepository competenciaRepository,
			FeedbackRepository feedbackRepository) {
		List<Feedback> feedbackList = new ArrayList<>();
		for (String idFeedback : idFeedbacks) {
			Optional<Feedback> feedback = feedbackRepository.findById(Long.valueOf(idFeedback));

			if (feedback.isEmpty()) {
				return null;
			}
			feedbackList.add(feedback.get());
		}

		Competencia competencia = new Competencia();
		competencia.setNome(nome);
		competencia.setConceituacao(conceituacao);
		competencia.setTipo(TipoCompetencia.valueOf(tipo));
		competencia.setFeedbacks(feedbackList);

		competencia.getFeedbacks().forEach(f -> f.setCompetencia(competencia));

		return competencia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConceituacao() {
		return conceituacao;
	}

	public void setConceituacao(String conceituacao) {
		this.conceituacao = conceituacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<String> getIdFeedbacks() {
		return idFeedbacks;
	}

	public void setIdFeedbacks(List<String> idFeedbacks) {
		this.idFeedbacks = idFeedbacks;
	}
}
