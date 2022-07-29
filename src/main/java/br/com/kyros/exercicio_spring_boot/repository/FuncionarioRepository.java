package br.com.kyros.exercicio_spring_boot.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.kyros.exercicio_spring_boot.model.Funcionario;
import br.com.kyros.exercicio_spring_boot.model.StatusFuncionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	@Query("select f from Funcionario f where (:nome = null or f.nome = :nome) "
			+ "and (cast(:dataDeAdmissao as date) = null or f.dataDeAdmissao >= cast(:dataDeAdmissao as date)) "
			+ "and (:liderId = null or f.lider.id = :liderId) "
			+ "and (:status = null or f.status = :status)")
	Page<Funcionario> findByNomeDataDeAdmissaoLiderStatus(@Param("nome") String nome,
			@Param("dataDeAdmissao") LocalDate dataDeAdmissao, @Param("liderId") Long liderId,
			@Param("status") StatusFuncionario status, Pageable paginacao);

	Optional<Funcionario> findByNumeroDeRegistro(Integer numeroDeRegistro);
	
	Optional<Funcionario> findByCpf(String cpf);
}
