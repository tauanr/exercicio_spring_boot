package br.com.kyros.exercicio_spring_boot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.kyros.exercicio_spring_boot.model.Competencia;
import br.com.kyros.exercicio_spring_boot.model.TipoCompetencia;

@Repository
public interface CompetenciaRepository extends JpaRepository<Competencia, Long> {

	@Query("select c from Competencia c where (:nome = null or c.nome = :nome) "
			+ "and (:tipo = null or c.tipo = :tipo)")
	Page<Competencia> findByNomeTipo(@Param("nome") String nome, @Param("tipo") TipoCompetencia tipo,
			Pageable paginacao);

}
