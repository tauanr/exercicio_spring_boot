package br.com.kyros.exercicio_spring_boot.controller.dto;

import org.springframework.data.domain.Page;

import br.com.kyros.exercicio_spring_boot.model.Competencia;

public class CompetenciaDto {

	private Long id;
	private String nome;
	private String conceituacao;
	private String tipo;

	public CompetenciaDto(Competencia competencia) {
		this.id = competencia.getId();
		this.nome = competencia.getNome();
		this.conceituacao = competencia.getConceituacao();
		this.tipo = competencia.getTipo().toString();
	}

	public static Page<CompetenciaDto> convert(Page<Competencia> competenciaPage) {
		return competenciaPage.map(CompetenciaDto::new);
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
