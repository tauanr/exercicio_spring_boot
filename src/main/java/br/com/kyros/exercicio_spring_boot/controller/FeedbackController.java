package br.com.kyros.exercicio_spring_boot.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.kyros.exercicio_spring_boot.controller.dto.FeedbackDto;
import br.com.kyros.exercicio_spring_boot.controller.dto.FeedbackListFuncionarioDto;
import br.com.kyros.exercicio_spring_boot.controller.form.FeedbackForm;
import br.com.kyros.exercicio_spring_boot.model.Feedback;
import br.com.kyros.exercicio_spring_boot.repository.CompetenciaRepository;
import br.com.kyros.exercicio_spring_boot.repository.FeedbackRepository;
import br.com.kyros.exercicio_spring_boot.repository.FuncionarioRepository;
import br.com.kyros.exercicio_spring_boot.validation.IdExistsAnnotation;

@RestController
@Validated
public class FeedbackController {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private CompetenciaRepository competenciaRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@PostMapping("/feedback")
	public ResponseEntity<FeedbackDto> postFeedback(@RequestBody @Valid FeedbackForm feedbackForm) {
		Feedback feedback = feedbackForm.toFeedback(feedbackRepository, competenciaRepository, funcionarioRepository);
		if (feedback == null) {
			return ResponseEntity.badRequest().build();
		}

		feedbackRepository.save(feedback);
		return ResponseEntity.status(HttpStatus.CREATED).body(new FeedbackDto(feedback));
	}

	@GetMapping("/feedbacks/{id}")
	public ResponseEntity<List<FeedbackListFuncionarioDto>> listFeedbacksFuncionario(@PathVariable @Pattern(regexp = "[0-9]{1,18}") @IdExistsAnnotation(entity = "Funcionario") String id) {
		Pageable paginacao = PageRequest.of(0, 30, Sort.by("dataDeRegistroDoFeedback").descending());
		Page<Feedback> feedbackPage = feedbackRepository
				.findBySubordinado_IdAndDataDeRegistroDoFeedbackGreaterThanEqual(Long.valueOf(id), LocalDate.now().minusYears(1),
						paginacao);
		Page<FeedbackListFuncionarioDto> dtoPage = FeedbackListFuncionarioDto.convert(feedbackPage);

		return ResponseEntity.ok().body(dtoPage.getContent());
	}
}
