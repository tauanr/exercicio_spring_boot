package br.com.kyros.exercicio_spring_boot.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kyros.exercicio_spring_boot.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

	Page<Feedback> findBySubordinado_IdAndDataDeRegistroDoFeedbackGreaterThanEqual(Long id, LocalDate dataDeRegistroDoFeedback, Pageable paginacao);

}
