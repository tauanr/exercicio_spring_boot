package br.com.kyros.exercicio_spring_boot.controller.dto;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import br.com.kyros.exercicio_spring_boot.model.Feedback;

public class FeedbackListFuncionarioDto {
	
	private String autor;
	private String destinatario;
	private LocalDate dataDeOcorrencia;
	private LocalDate dataDeRegistroDoFeedback;
	private String nomeCompetencia;
	private String tipoCompetencia;
	private String conceituacaoCompetencia;
	
	public FeedbackListFuncionarioDto(Feedback feedback) {
		autor = feedback.getAutor().getNome();
		destinatario = feedback.getSubordinado().getNome();
		dataDeOcorrencia = feedback.getDataDeOcorrencia();
		dataDeRegistroDoFeedback = feedback.getDataDeRegistroDoFeedback();
		nomeCompetencia = feedback.getCompetencia().getNome();
		tipoCompetencia = feedback.getCompetencia().getTipo().toString();
		conceituacaoCompetencia = feedback.getCompetencia().getConceituacao();
	}
	
	public static Page<FeedbackListFuncionarioDto> convert(Page<Feedback> feedbackPage) {
		return feedbackPage.map(FeedbackListFuncionarioDto::new);
	}
	
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public LocalDate getDataDeOcorrencia() {
		return dataDeOcorrencia;
	}
	public void setDataDeOcorrencia(LocalDate dataDeOcorrencia) {
		this.dataDeOcorrencia = dataDeOcorrencia;
	}
	public LocalDate getDataDeRegistroDoFeedback() {
		return dataDeRegistroDoFeedback;
	}
	public void setDataDeRegistroDoFeedback(LocalDate dataDeRegistroDoFeedback) {
		this.dataDeRegistroDoFeedback = dataDeRegistroDoFeedback;
	}
	public String getNomeCompetencia() {
		return nomeCompetencia;
	}
	public void setNomeCompetencia(String nomeCompetencia) {
		this.nomeCompetencia = nomeCompetencia;
	}
	public String getTipoCompetencia() {
		return tipoCompetencia;
	}
	public void setTipoCompetencia(String tipoCompetencia) {
		this.tipoCompetencia = tipoCompetencia;
	}
	public String getConceituacaoCompetencia() {
		return conceituacaoCompetencia;
	}
	public void setConceituacaoCompetencia(String conceituacaoCompetencia) {
		this.conceituacaoCompetencia = conceituacaoCompetencia;
	}								
}
