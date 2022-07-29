package br.com.kyros.exercicio_spring_boot.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@Column(unique = true)
	private Integer numeroDeRegistro;

	@Column(unique = true)
	private String cpf;
	private LocalDate dataDeAdmissao;
	private BigDecimal salario;

	@ManyToOne
	private Funcionario lider;

	@OneToMany(mappedBy = "lider")
	private List<Funcionario> subordinados;

	@Enumerated(EnumType.STRING)
	private StatusFuncionario status;
	private LocalDate dataDeDesligamento;

	@Enumerated(EnumType.STRING)
	private Genero genero;

	@ManyToOne
	private Departamento departamento;

	@OneToMany(mappedBy = "subordinado")
	private List<Feedback> feedbacksRecebidos;

	@OneToMany(mappedBy = "autor")
	private List<Feedback> feedbacksEnviados;

	public Funcionario() {
	}

	public Funcionario(String nome, Integer numeroDeRegistro, String cpf, LocalDate dataDeAdmissao, BigDecimal salario,
			StatusFuncionario status, Genero genero) {
		this.nome = nome;
		this.numeroDeRegistro = numeroDeRegistro;
		this.cpf = cpf;
		this.dataDeAdmissao = dataDeAdmissao;
		this.salario = salario;
		this.status = status;
		this.genero = genero;
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

	public Integer getNumeroDeRegistro() {
		return numeroDeRegistro;
	}

	public void setNumeroDeRegistro(Integer numeroDeRegistro) {
		this.numeroDeRegistro = numeroDeRegistro;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataDeAdmissao() {
		return dataDeAdmissao;
	}

	public void setDataDeAdmissao(LocalDate dataDeAdmissao) {
		this.dataDeAdmissao = dataDeAdmissao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Funcionario getLider() {
		return lider;
	}

	public void setLider(Funcionario lider) {
		this.lider = lider;
	}

	public List<Funcionario> getSubordinados() {
		return subordinados;
	}

	public void setSubordinados(List<Funcionario> subordinados) {
		this.subordinados = subordinados;
	}

	public StatusFuncionario getStatus() {
		return status;
	}

	public void setStatus(StatusFuncionario status) {
		this.status = status;
	}

	public LocalDate getDataDeDesligamento() {
		return dataDeDesligamento;
	}

	public void setDataDeDesligamento(LocalDate dataDeDesligamento) {
		this.dataDeDesligamento = dataDeDesligamento;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Feedback> getFeedbacksRecebidos() {
		return feedbacksRecebidos;
	}

	public void setFeedbacksRecebidos(List<Feedback> feedbacksRecebidos) {
		this.feedbacksRecebidos = feedbacksRecebidos;
	}

	public List<Feedback> getFeedbacksEnviados() {
		return feedbacksEnviados;
	}

	public void setFeedbacksEnviados(List<Feedback> feedbacksEnviados) {
		this.feedbacksEnviados = feedbacksEnviados;
	}

}
