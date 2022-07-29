package br.com.kyros.exercicio_spring_boot.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoFeedback tipo;
	private LocalDate dataDeOcorrencia;
	private String evento;

	@ManyToOne
	private Funcionario subordinado;

	@ManyToOne
	private Funcionario autor;
	private LocalDate dataDeRegistroDoFeedback;

	@ManyToOne
	private Competencia competencia;
	
	public Feedback() {	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoFeedback getTipo() {
		return tipo;
	}

	public void setTipo(TipoFeedback tipo) {
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

	public Funcionario getSubordinado() {
		return subordinado;
	}

	public void setSubordinado(Funcionario subordinado) {
		this.subordinado = subordinado;
	}

	public Funcionario getAutor() {
		return autor;
	}

	public void setAutor(Funcionario autor) {
		this.autor = autor;
	}

	public LocalDate getDataDeRegistroDoFeedback() {
		return dataDeRegistroDoFeedback;
	}

	public void setDataDeRegistroDoFeedback(LocalDate dataDeRegistroDoFeedback) {
		this.dataDeRegistroDoFeedback = dataDeRegistroDoFeedback;
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

}
