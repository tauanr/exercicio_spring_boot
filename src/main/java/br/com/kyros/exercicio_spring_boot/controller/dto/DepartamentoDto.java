package br.com.kyros.exercicio_spring_boot.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.kyros.exercicio_spring_boot.model.Departamento;

public class DepartamentoDto {

	private Long id;
	private String nome;
	private Integer codigoDaFolhaDePagamento;
	private String status;
	private List<String> funcionariosNomes = new ArrayList<>();

	public DepartamentoDto(Departamento departamento) {
		this.id = departamento.getId();
		this.nome = departamento.getNome();
		this.codigoDaFolhaDePagamento = departamento.getCodigoDaFolhaDePagamento();
		this.status = departamento.getStatus().toString();
		departamento.getFuncionarios().forEach(f -> {
			this.funcionariosNomes.add(f.getNome());
		});
	}

	public static Page<DepartamentoDto> convert(Page<Departamento> departamentoPage) {
		return departamentoPage.map(DepartamentoDto::new);
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

	public Integer getCodigoDaFolhaDePagamento() {
		return codigoDaFolhaDePagamento;
	}

	public void setCodigoDaFolhaDePagamento(Integer codigoDaFolhaDePagamento) {
		this.codigoDaFolhaDePagamento = codigoDaFolhaDePagamento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getFuncionariosNomes() {
		return funcionariosNomes;
	}

	public void setFuncionariosNomes(List<String> funcionariosNomes) {
		this.funcionariosNomes = funcionariosNomes;
	}
}
