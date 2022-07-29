package br.com.kyros.exercicio_spring_boot.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.kyros.exercicio_spring_boot.controller.dto.DepartamentoDto;
import br.com.kyros.exercicio_spring_boot.controller.form.DepartamentoForm;
import br.com.kyros.exercicio_spring_boot.controller.form.DepartamentoUpdateForm;
import br.com.kyros.exercicio_spring_boot.model.Departamento;
import br.com.kyros.exercicio_spring_boot.repository.DepartamentoRepository;
import br.com.kyros.exercicio_spring_boot.repository.FuncionarioRepository;
import br.com.kyros.exercicio_spring_boot.validation.IdExistsAnnotation;

@RestController
@Validated
public class DepartamentoController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@GetMapping("/departamentos")
	public Page<DepartamentoDto> listDepartamento(
			@PageableDefault(sort = "nome", direction = Direction.ASC) Pageable paginacao) {

		Page<DepartamentoDto> departamentoDtoPage = DepartamentoDto.convert(departamentoRepository.findAll(paginacao));

		return departamentoDtoPage;
	}

	@PostMapping("/departamento")
	public ResponseEntity<DepartamentoDto> postDepartamento(@RequestBody @Valid DepartamentoForm departamentoForm,
			UriComponentsBuilder uriComponentsBuilder) {

		Departamento departamento = departamentoForm.toDepartamento(departamentoRepository, funcionarioRepository);

		if (departamento == null) {
			return ResponseEntity.badRequest().build();
		}

		departamentoRepository.save(departamento);

		URI uri = uriComponentsBuilder.path("departamento/{id}").buildAndExpand(departamento.getId()).toUri();
		return ResponseEntity.created(uri).body(new DepartamentoDto(departamento));
	}

	@DeleteMapping("/departamento/{id}")
	public ResponseEntity<?> deleteDepartamento(
			@PathVariable @Pattern(regexp = "[0-9]{1,18}") @IdExistsAnnotation(entity = "Departamento") String id) {
		Optional<Departamento> departamento = departamentoRepository.findById(Long.valueOf(id));
		if (departamento.isPresent()) {
			departamento.get().getFuncionarios().forEach(f -> {
				f.setDepartamento(null);
			});
			departamentoRepository.delete(departamento.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/departamento")
	public ResponseEntity<DepartamentoDto> updateDepartamento(
			@RequestBody @Valid DepartamentoUpdateForm departamentoUpdateForm) {
		Departamento departamento = departamentoUpdateForm.toDepartamento(departamentoRepository,
				funcionarioRepository);
		if (departamento == null) {
			return ResponseEntity.badRequest().build();
		}

		departamentoRepository.save(departamento);
		return ResponseEntity.ok().body(new DepartamentoDto(departamento));
	}
}
