package br.com.kyros.exercicio_spring_boot.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@Column(unique = true)
	private Integer codigoDaFolhaDePagamento;

	@Enumerated(EnumType.STRING)
	private StatusDepartamento status;

	@OneToMany(mappedBy = "departamento")
	private List<Funcionario> funcionarios;

	public Departamento() {
	}

	public Departamento(String nome, Integer codigoDaFolhaDePagamento, StatusDepartamento status,
			List<Funcionario> funcionarios) {
		this.nome = nome;
		this.codigoDaFolhaDePagamento = codigoDaFolhaDePagamento;
		this.status = status;
		this.funcionarios = funcionarios;
		funcionarios.forEach(f -> {
			f.setDepartamento(this);
		});
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

	public StatusDepartamento getStatus() {
		return status;
	}

	public void setStatus(StatusDepartamento status) {
		this.status = status;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
