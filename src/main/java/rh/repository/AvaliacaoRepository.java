package rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rh.model.Avaliacao;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{
    List<Avaliacao> findByIdFuncionarioOrderByIdDesc(Long id);
}
