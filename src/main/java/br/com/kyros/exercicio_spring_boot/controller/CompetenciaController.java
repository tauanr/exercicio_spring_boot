package br.com.kyros.exercicio_spring_boot.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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

import br.com.kyros.exercicio_spring_boot.controller.dto.CompetenciaDto;
import br.com.kyros.exercicio_spring_boot.controller.form.CompetenciaForm;
import br.com.kyros.exercicio_spring_boot.controller.form.CompetenciaUpdateForm;
import br.com.kyros.exercicio_spring_boot.model.Competencia;
import br.com.kyros.exercicio_spring_boot.model.TipoCompetencia;
import br.com.kyros.exercicio_spring_boot.repository.CompetenciaRepository;
import br.com.kyros.exercicio_spring_boot.repository.FeedbackRepository;
import br.com.kyros.exercicio_spring_boot.validation.IdExistsAnnotation;

@RestController
@Validated
public class CompetenciaController {

	@Autowired
	private CompetenciaRepository competenciaRepository;

	@Autowired
	private FeedbackRepository feedbackRepository;

	@GetMapping("/competencias")
	public Page<CompetenciaDto> listCompetencia(
			@Pattern(regexp = "[a-zA-Z]{4,20}|^$") @RequestParam(required = false) @Pattern(regexp = "[a-zA-Z ]{4,20}") String nome,
			@RequestParam(required = false) @Pattern(regexp = "(LIDERANCA)|(ORGANIZACIONAL)|(OPERACIONAL)") String tipo,
			@PageableDefault(sort = "nome", direction = Direction.ASC) Pageable paginacao) {

		TipoCompetencia tipoCompetencia = (tipo == null ? null : TipoCompetencia.valueOf(tipo));

		return CompetenciaDto.convert(competenciaRepository.findByNomeTipo(nome, tipoCompetencia, paginacao));
	}

	@PostMapping("/competencia")
	public ResponseEntity<CompetenciaDto> postCompetencia(@Valid @RequestBody CompetenciaForm competenciaForm,
			UriComponentsBuilder uriComponentsBuilder) {

		Competencia competencia = competenciaForm.toCompetencia(competenciaRepository, feedbackRepository);

		if (competencia == null) {
			return ResponseEntity.badRequest().build();
		}
		competenciaRepository.save(competencia);

		return ResponseEntity.status(HttpStatus.CREATED).body(new CompetenciaDto(competencia));
	}

	@DeleteMapping("/competencia/{id}")
	public ResponseEntity<?> deleteCompetencia(
			@PathVariable @Pattern(regexp = "[0-9]{1,18}") @IdExistsAnnotation(entity = "Competencia") String id) {
		Optional<Competencia> competencia = competenciaRepository.findById(Long.valueOf(id));

		if (competencia.isPresent()) {
			competencia.get().getFeedbacks().forEach(f -> {
				f.setCompetencia(null);
			});
			competenciaRepository.deleteById(Long.valueOf(id));
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/competencia")
	public ResponseEntity<CompetenciaDto> updateCompetencia(
			@RequestBody @Valid CompetenciaUpdateForm competenciaUpdateForm) {
		Competencia competencia = competenciaUpdateForm.toCompetencia(competenciaRepository, feedbackRepository);
		if (competencia == null) {
			return ResponseEntity.badRequest().build();
		}

		competenciaRepository.save(competencia);
		return ResponseEntity.ok().body(new CompetenciaDto(competencia));
	}
}
