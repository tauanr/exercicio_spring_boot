package br.com.kyros.exercicio_spring_boot.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.kyros.exercicio_spring_boot.model.Departamento;
import br.com.kyros.exercicio_spring_boot.model.Funcionario;
import br.com.kyros.exercicio_spring_boot.model.Genero;
import br.com.kyros.exercicio_spring_boot.model.StatusFuncionario;
import br.com.kyros.exercicio_spring_boot.repository.DepartamentoRepository;
import br.com.kyros.exercicio_spring_boot.repository.FuncionarioRepository;
import br.com.kyros.exercicio_spring_boot.validation.FieldMustBeUniqueInDbAnnotation;
import br.com.kyros.exercicio_spring_boot.validation.IdExistsAnnotation;

public class FuncionarioForm {
	protected final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@NotBlank
	@Pattern(regexp = "[a-zA-Z]{5,40}")
	protected String nome;

	@NotBlank
	@Pattern(regexp = "[0-9]{1,10}")
	@FieldMustBeUniqueInDbAnnotation(uniqueField = "numeroDeRegistro")
	protected String numeroDeRegistro;

	@NotBlank
	@Pattern(regexp = "[0-9]{9}-[0-9]{2}")
	@FieldMustBeUniqueInDbAnnotation(uniqueField = "cpf")
	protected String cpf;

	@NotBlank
	@Pattern(regexp = "((([0-2][0-9])|(3[0-1]))/((0[0-9])|(1[0-2]))/[0-9]{4})|(^$)")
	protected String dataDeAdmissao;

	@NotBlank
	@Pattern(regexp = "[0-9]{3,7}")
	protected String salario;

	@NotBlank
	@Pattern(regexp = "[0-9]{1,10}|^$")
	@IdExistsAnnotation(entity = "Funcionario")
	protected String idLider;

	@NotBlank
	@Pattern(regexp = "ATIVO|INATIVO|DESLIGADO")
	protected String status;

	@Pattern(regexp = "((([0-2][0-9])|(3[0-1]))/((0[0-9])|(1[0-2]))/[0-9]{4})|(^$)")
	protected String dataDeDesligamento;

	@NotBlank
	@Pattern(regexp = "MASCULINO|FEMININO")
	protected String genero;

	@NotBlank
	@Pattern(regexp = "[0-9]{1,10}")
	@IdExistsAnnotation(entity = "Departamento")
	protected String idDepartamento;

	public Funcionario toFuncionario(DepartamentoRepository departamentoRepository,
			FuncionarioRepository funcionarioRepository) {

		Funcionario funcionario = new Funcionario(nome, Integer.valueOf(numeroDeRegistro), cpf,
				LocalDate.parse(dataDeAdmissao, formatter), new BigDecimal(salario), StatusFuncionario.valueOf(status),
				Genero.valueOf(genero));

		Optional<Departamento> departamento = departamentoRepository.findById(Long.valueOf(this.idDepartamento));

		if (departamento.isEmpty()) {
			return null;
		}
		funcionario.setDepartamento(departamento.get());
		departamento.get().getFuncionarios().add(funcionario);

		if ((!dataDeDesligamento.equals("") && !status.equals("DESLIGADO"))
				|| (status.equals("DESLIGADO") && dataDeDesligamento.equals(""))) {
			return null;
		}

		if (!dataDeDesligamento.equals("") && status.equals("DESLIGADO")) {
			funcionario.setDataDeDesligamento(LocalDate.parse(dataDeDesligamento, formatter));
		}

		if (idLider != "") {
			Optional<Funcionario> lider = funcionarioRepository.findById(Long.valueOf(this.idLider));
			if (lider.isEmpty()) {
				return null;
			}
			funcionario.setLider(lider.get());
			lider.get().getSubordinados().add(funcionario);
		}

		return funcionario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroDeRegistro() {
		return numeroDeRegistro;
	}

	public void setNumeroDeRegistro(String numeroDeRegistro) {
		this.numeroDeRegistro = numeroDeRegistro;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataDeAdmissao() {
		return dataDeAdmissao;
	}

	public void setDataDeAdmissao(String dataDeAdmissao) {
		this.dataDeAdmissao = dataDeAdmissao;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	public String getIdLider() {
		return idLider;
	}

	public void setIdLider(String idLider) {
		this.idLider = idLider;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDataDeDesligamento() {
		return dataDeDesligamento;
	}

	public void setDataDeDesligamento(String dataDeDesligamento) {
		this.dataDeDesligamento = dataDeDesligamento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(String idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
}
