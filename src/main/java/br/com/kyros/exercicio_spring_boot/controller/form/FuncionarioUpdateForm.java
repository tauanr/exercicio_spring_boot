package br.com.kyros.exercicio_spring_boot.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.kyros.exercicio_spring_boot.model.Departamento;
import br.com.kyros.exercicio_spring_boot.model.Funcionario;
import br.com.kyros.exercicio_spring_boot.model.Genero;
import br.com.kyros.exercicio_spring_boot.model.StatusFuncionario;
import br.com.kyros.exercicio_spring_boot.repository.DepartamentoRepository;
import br.com.kyros.exercicio_spring_boot.repository.FuncionarioRepository;
import br.com.kyros.exercicio_spring_boot.validation.IdExistsAnnotation;

public class FuncionarioUpdateForm extends FuncionarioForm {

	@NotBlank
	@Pattern(regexp = "[0-9]{1,18}")
	@IdExistsAnnotation(entity = "Funcionario")
	private String id;

	@Override
	public Funcionario toFuncionario(DepartamentoRepository departamentoRepository,
			FuncionarioRepository funcionarioRepository) {
		Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(Long.valueOf(id));
		if (funcionarioOptional.isEmpty()) {
			return null;
		}
		Funcionario funcionario = funcionarioOptional.get();

		funcionario.setNome(nome);
		funcionario.setNumeroDeRegistro(Integer.valueOf(numeroDeRegistro));
		funcionario.setCpf(cpf);
		funcionario.setDataDeAdmissao(LocalDate.parse(dataDeAdmissao, formatter));
		funcionario.setSalario(new BigDecimal(salario));

		if (!idLider.equals("")) {
			Optional<Funcionario> lider = funcionarioRepository.findById(Long.valueOf(idLider));
			if (lider.isEmpty()) {
				return null;
			}
			funcionario.setLider(lider.get());
			lider.get().getSubordinados().add(funcionario);
		}
		funcionario.setStatus(StatusFuncionario.valueOf(status));

		if ((!dataDeDesligamento.equals("") && !status.equals("DESLIGADO"))
				|| (status.equals("DESLIGADO") && dataDeDesligamento.equals(""))) {
			return null;
		}
		if (!dataDeDesligamento.equals("") && status.equals("DESLIGADO")) {
			funcionario.setDataDeDesligamento(LocalDate.parse(dataDeDesligamento, formatter));
		} else {
			funcionario.setDataDeDesligamento(null);
		}

		funcionario.setGenero(Genero.valueOf(genero));

		Optional<Departamento> departamento = departamentoRepository.findById(Long.valueOf(idDepartamento));
		if (departamento.isEmpty()) {
			return null;
		}
		funcionario.setDepartamento(departamento.get());
		departamento.get().getFuncionarios().add(funcionario);

		return funcionario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
