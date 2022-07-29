package br.com.kyros.exercicio_spring_boot.controller.form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.constraints.Pattern;

import br.com.kyros.exercicio_spring_boot.model.Competencia;
import br.com.kyros.exercicio_spring_boot.model.Feedback;
import br.com.kyros.exercicio_spring_boot.model.Funcionario;
import br.com.kyros.exercicio_spring_boot.model.TipoFeedback;
import br.com.kyros.exercicio_spring_boot.repository.CompetenciaRepository;
import br.com.kyros.exercicio_spring_boot.repository.FeedbackRepository;
import br.com.kyros.exercicio_spring_boot.repository.FuncionarioRepository;
import br.com.kyros.exercicio_spring_boot.validation.IdExistsAnnotation;

public class FeedbackForm {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Pattern(regexp = "(ADVERTENCIA)|(ELOGIO)")
	private String tipo;

	@Pattern(regexp = "(([0-2][0-9])|(3[0-1]))/((0[0-9])|(1[0-2]))/[0-9]{4}")
	private String dataDeOcorrencia;

	@Pattern(regexp = "[a-zA-Z ]{3,30}")
	private String evento;

	@Pattern(regexp = "[0-9]{1,18}")
	@IdExistsAnnotation(entity = "Funcionario")
	private String idDestinatario;

	@Pattern(regexp = "[0-9]{1,18}")
	@IdExistsAnnotation(entity = "Funcionario")
	private String idAutor;

	@Pattern(regexp = "[0-9]{1,18}")
	@IdExistsAnnotation(entity = "Competencia")
	private String idCompetencia;

	public Feedback toFeedback(FeedbackRepository feedbackRepository, CompetenciaRepository competenciaRepository,
			FuncionarioRepository funcionarioRepository) {
		Optional<Funcionario> destinatario = funcionarioRepository.findById(Long.valueOf(idDestinatario));
		if (destinatario.isEmpty()) {
			return null;
		}

		Optional<Funcionario> autor = funcionarioRepository.findById(Long.valueOf(idAutor));
		if (autor.isEmpty()) {
			return null;
		}

		if (destinatario.get().getLider() == null || destinatario.get().getLider().getId() != autor.get().getId()) {
			return null;
		}

		Optional<Competencia> competencia = competenciaRepository.findById(Long.valueOf(idCompetencia));
		if (competencia.isEmpty()) {
			return null;
		}

		Feedback feedback = new Feedback();
		feedback.setTipo(TipoFeedback.valueOf(tipo));
		feedback.setDataDeOcorrencia(LocalDate.parse(dataDeOcorrencia, formatter));
		feedback.setEvento(evento);
		feedback.setSubordinado(destinatario.get());
		feedback.setAutor(autor.get());
		feedback.setCompetencia(competencia.get());
		feedback.setDataDeRegistroDoFeedback(LocalDate.now());

		feedback.getSubordinado().getFeedbacksRecebidos().add(feedback);
		feedback.getAutor().getFeedbacksEnviados().add(feedback);
		feedback.getCompetencia().getFeedbacks().add(feedback);

		return feedback;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDataDeOcorrencia() {
		return dataDeOcorrencia;
	}

	public void setDataDeOcorrencia(String dataDeOcorrencia) {
		this.dataDeOcorrencia = dataDeOcorrencia;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getIdDestinatario() {
		return idDestinatario;
	}

	public void setIdDestinatario(String idDestinatario) {
		this.idDestinatario = idDestinatario;
	}

	public String getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(String idAutor) {
		this.idAutor = idAutor;
	}

	public String getIdCompetencia() {
		return idCompetencia;
	}

	public void setIdCompetencia(String idCompetencia) {
		this.idCompetencia = idCompetencia;
	}
}
