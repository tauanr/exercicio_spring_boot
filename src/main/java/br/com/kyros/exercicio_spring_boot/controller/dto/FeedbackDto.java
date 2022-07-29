package br.com.kyros.exercicio_spring_boot.controller.dto;

import java.time.LocalDate;

import br.com.kyros.exercicio_spring_boot.model.Feedback;

public class FeedbackDto {

	private Long id;
	private String tipo;
	private LocalDate dataDeOcorrencia;
	private String evento;
	private String subordinado;
	private String autor;
	private LocalDate dataDeRegistroDoFeedback;
	private String nomeCompetencia;
	private String tipoCompetencia;
	private String conceituacaoCompetencia;

	public FeedbackDto(Feedback feedback) {
		id = feedback.getId();
		tipo = feedback.getTipo().toString();
		dataDeOcorrencia = feedback.getDataDeOcorrencia();
		evento = feedback.getEvento();
		subordinado = feedback.getSubordinado().getNome();
		autor = feedback.getAutor().getNome();
		dataDeRegistroDoFeedback = feedback.getDataDeRegistroDoFeedback();
		nomeCompetencia = feedback.getCompetencia().getNome();
		tipoCompetencia = feedback.getCompetencia().getTipo().toString();
		conceituacaoCompetencia = feedback.getCompetencia().getConceituacao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDate getDataDeOcorrencia() {
		return dataDeOcorrencia;
	}

	public void setDataDeOcorrencia(LocalDate dataDeOcorrencia) {
		this.dataDeOcorrencia = dataDeOcorrencia;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getSubordinado() {
		return subordinado;
	}

	public void setSubordinado(String subordinado) {
		this.subordinado = subordinado;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
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
