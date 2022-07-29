package br.com.kyros.exercicio_spring_boot.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Competencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String conceituacao;

	@Enumerated(EnumType.STRING)
	private TipoCompetencia tipo;

	@OneToMany(mappedBy = "competencia")
	private List<Feedback> feedbacks;

	public Competencia() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TipoCompetencia getTipo() {
		return tipo;
	}

	public void setTipo(TipoCompetencia tipo) {
		this.tipo = tipo;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

}
