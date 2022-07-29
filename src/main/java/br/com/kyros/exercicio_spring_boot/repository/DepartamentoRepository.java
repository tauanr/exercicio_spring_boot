package br.com.kyros.exercicio_spring_boot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kyros.exercicio_spring_boot.model.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

	Optional<Departamento> findByCodigoDaFolhaDePagamento(Integer codigoDaFolhaDePagamento);
}
