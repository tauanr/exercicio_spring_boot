package br.com.kyros.exercicio_spring_boot.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.kyros.exercicio_spring_boot.controller.dto.FuncionarioDto;
import br.com.kyros.exercicio_spring_boot.controller.form.FuncionarioForm;
import br.com.kyros.exercicio_spring_boot.controller.form.FuncionarioUpdateForm;
import br.com.kyros.exercicio_spring_boot.model.Funcionario;
import br.com.kyros.exercicio_spring_boot.model.StatusFuncionario;
import br.com.kyros.exercicio_spring_boot.repository.DepartamentoRepository;
import br.com.kyros.exercicio_spring_boot.repository.FuncionarioRepository;
import br.com.kyros.exercicio_spring_boot.validation.IdExistsAnnotation;

@RestController
@Validated
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@GetMapping("/funcionario/{id}")
	public ResponseEntity<FuncionarioDto> getFuncionarioById(
			@PathVariable @Pattern(regexp = "[0-9]{1,18}") @IdExistsAnnotation(entity = "Funcionario") String id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(Long.valueOf(id));
		if (funcionario.isPresent()) {
			return ResponseEntity.ok().body(new FuncionarioDto(funcionario.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/funcionario")
	public ResponseEntity<FuncionarioDto> postNewFuncionario(@RequestBody @Valid FuncionarioForm funcionarioForm,
			UriComponentsBuilder uriComponentsBuilder) {

		Funcionario funcionario = funcionarioForm.toFuncionario(departamentoRepository, funcionarioRepository);
		if (funcionario == null) {
			return ResponseEntity.badRequest().build();
		}

		funcionarioRepository.save(funcionario);

		URI uri = uriComponentsBuilder.path("funcionario/{id}").buildAndExpand(funcionario.getId()).toUri();
		return ResponseEntity.created(uri).body(new FuncionarioDto(funcionario));
	}

	@PutMapping("/funcionario")
	public ResponseEntity<FuncionarioDto> updateFuncionario(
			@RequestBody @Valid FuncionarioUpdateForm funcionarioUpdateForm) {
		Funcionario funcionario = funcionarioUpdateForm.toFuncionario(departamentoRepository, funcionarioRepository);
		if (funcionario == null) {
			return ResponseEntity.badRequest().build();
		}

		funcionarioRepository.save(funcionario);
		return ResponseEntity.ok().body(new FuncionarioDto(funcionario));
	}

	@DeleteMapping("/funcionario/{id}")
	public ResponseEntity<?> remover(
			@PathVariable @Pattern(regexp = "[0-9]{1,18}") @IdExistsAnnotation(entity = "Funcionario") String id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(Long.valueOf(id));
		if (funcionario.isPresent()) {
			funcionario.get().getSubordinados().forEach(f -> {
				f.setLider(null);
			});
			if (funcionario.get().getLider() != null) {
				funcionario.get().getLider().getSubordinados().remove(funcionario.get());
			}
			funcionarioRepository.deleteById(Long.valueOf(id));
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/funcionarios")
	public Page<FuncionarioDto> listFuncionario(
			@RequestParam(required = false) @Pattern(regexp = "([a-zA-Z]{5,40})") String nome,
			@RequestParam(required = false) @Pattern(regexp = "((([0-2][0-9])|(3[0-1]))/((0[0-9])|(1[0-2]))/[0-9]{4})") String dataDeAdmissao,
			@RequestParam(required = false) @Pattern(regexp = "[0-9]{1,18}") String liderId,
			@RequestParam(required = false) @Pattern(regexp = "ATIVO|INATIVO|DESLIGADO") String status,
			@PageableDefault(sort = "nome", direction = Direction.ASC) Pageable paginacao) {

		LocalDate dataDeAdmissao2 = null;
		if (dataDeAdmissao != null) {
			dataDeAdmissao2 = LocalDate.parse(dataDeAdmissao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}

		Long liderId2 = null;
		if (liderId != null) {
			liderId2 = Long.valueOf(liderId);
		}

		StatusFuncionario status2 = null;
		if (status != null) {
			status2 = StatusFuncionario.valueOf(status);
		}

		Page<FuncionarioDto> funcionarioDtoPage = FuncionarioDto.convert(funcionarioRepository
				.findByNomeDataDeAdmissaoLiderStatus(nome, dataDeAdmissao2, liderId2, status2, paginacao));

		return funcionarioDtoPage;

	}
}
