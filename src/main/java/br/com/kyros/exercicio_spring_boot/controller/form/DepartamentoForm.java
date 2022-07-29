package br.com.kyros.exercicio_spring_boot.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;

import br.com.kyros.exercicio_spring_boot.model.Departamento;
import br.com.kyros.exercicio_spring_boot.model.Funcionario;
import br.com.kyros.exercicio_spring_boot.model.StatusDepartamento;
import br.com.kyros.exercicio_spring_boot.repository.DepartamentoRepository;
import br.com.kyros.exercicio_spring_boot.repository.FuncionarioRepository;
import br.com.kyros.exercicio_spring_boot.validation.FieldMustBeUniqueInDbAnnotation;
import br.com.kyros.exercicio_spring_boot.validation.IdExistsAnnotation;

public class DepartamentoForm {

	@Pattern(regexp = "[a-zA-Z]{3,50}")
	protected String nome;

	@Pattern(regexp = "[0-9]{1,10}")
	@FieldMustBeUniqueInDbAnnotation(uniqueField = "codigoDaFolhaDePagamento")
	protected String codigoDaFolhaDePagamento;

	@Pattern(regexp = "(ATIVO)|(INATIVO)")
	protected String status;

	protected List<@Pattern(regexp = "[0-9]{1,18}") @IdExistsAnnotation(entity = "Funcionario") String> funcionariosIds;

	public Departamento toDepartamento(DepartamentoRepository departamentoRepository,
			FuncionarioRepository funcionarioRepository) {
		List<Funcionario> funcionarios = new ArrayList<>();
		for (String id : funcionariosIds) {
			if (funcionarioRepository.findById(Long.valueOf(id)).isEmpty()) {
				return null;
			}
			funcionarios.add(funcionarioRepository.findById(Long.valueOf(id)).get());
		}
		return new Departamento(nome, Integer.valueOf(codigoDaFolhaDePagamento), StatusDepartamento.valueOf(status),
				funcionarios);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoDaFolhaDePagamento() {
		return codigoDaFolhaDePagamento;
	}

	public void setCodigoDaFolhaDePagamento(String codigoDaFolhaDePagamento) {
		this.codigoDaFolhaDePagamento = codigoDaFolhaDePagamento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getFuncionariosIds() {
		return funcionariosIds;
	}

	public void setFuncionariosIds(List<String> funcionariosIds) {
		this.funcionariosIds = funcionariosIds;
	}
}
