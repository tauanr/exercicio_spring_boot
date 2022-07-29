package br.com.kyros.exercicio_spring_boot.controller.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.kyros.exercicio_spring_boot.model.Departamento;
import br.com.kyros.exercicio_spring_boot.model.Funcionario;
import br.com.kyros.exercicio_spring_boot.model.StatusDepartamento;
import br.com.kyros.exercicio_spring_boot.repository.DepartamentoRepository;
import br.com.kyros.exercicio_spring_boot.repository.FuncionarioRepository;
import br.com.kyros.exercicio_spring_boot.validation.IdExistsAnnotation;

public class DepartamentoUpdateForm extends DepartamentoForm {

	@NotBlank
	@Pattern(regexp = "[0-9]{1,18}")
	@IdExistsAnnotation(entity = "Departamento")
	private String id;

	@Override
	public Departamento toDepartamento(DepartamentoRepository departamentoRepository,
			FuncionarioRepository funcionarioRepository) {
		Optional<Departamento> departamentoOptional = departamentoRepository.findById(Long.valueOf(getId()));
		if (departamentoOptional.isEmpty()) {
			return null;
		}
		Departamento departamento = departamentoOptional.get();

		List<Funcionario> funcionarios = new ArrayList<>();
		for (String id : super.funcionariosIds) {
			if (funcionarioRepository.findById(Long.valueOf(id)).isEmpty()) {
				return null;
			}
			funcionarios.add(funcionarioRepository.findById(Long.valueOf(id)).get());
		}

		departamento.setNome(super.nome);
		departamento.setCodigoDaFolhaDePagamento(Integer.valueOf(super.codigoDaFolhaDePagamento));
		departamento.setStatus(StatusDepartamento.valueOf(super.status));
		departamento.setFuncionarios(funcionarios);
		funcionarios.forEach(f -> {
			f.setDepartamento(departamento);
		});

		return departamento;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
