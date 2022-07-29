package br.com.kyros.exercicio_spring_boot.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;

import br.com.kyros.exercicio_spring_boot.model.Funcionario;

public class FuncionarioDto {

	private Long id;
	private String nome;
	private Integer numeroDeRegistro;
	private String cpf;
	private LocalDate dataDeAdmissao;
	private BigDecimal salario;
	private String nomeLider;
	private String status;
	private LocalDate dataDeDesligamento;
	private String genero;
	private String nomeDepartamento;

	public FuncionarioDto(Funcionario funcionario) {
		this.id = funcionario.getId();
		this.nome = funcionario.getNome();
		this.numeroDeRegistro = funcionario.getNumeroDeRegistro();
		this.cpf = funcionario.getCpf();
		this.dataDeAdmissao = funcionario.getDataDeAdmissao();
		this.salario = funcionario.getSalario();
		if (funcionario.getLider() != null) {
			this.nomeLider = funcionario.getLider().getNome();
		}
		this.status = funcionario.getStatus().toString();
		if (funcionario.getDataDeDesligamento() != null) {
			this.dataDeDesligamento = funcionario.getDataDeDesligamento();
		}
		this.genero = funcionario.getGenero().toString();

		if (funcionario.getDepartamento() != null) {
			this.nomeDepartamento = funcionario.getDepartamento().getNome();
		}

	}

	public static Page<FuncionarioDto> convert(Page<Funcionario> funcionarioPage) {
		return funcionarioPage.map(FuncionarioDto::new);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getNumeroDeRegistro() {
		return numeroDeRegistro;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataDeAdmissao() {
		return dataDeAdmissao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public String getNomeLider() {
		return nomeLider;
	}

	public String getStatus() {
		return status;
	}

	public LocalDate getDataDeDesligamento() {
		return dataDeDesligamento;
	}

	public String getGenero() {
		return genero;
	}

	public String getNomeDepartamento() {
		return nomeDepartamento;
	}

}
