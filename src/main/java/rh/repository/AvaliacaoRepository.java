package rh.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rh.model.Avaliacao;
import rh.model.Funcionario;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long>{
    List<Avaliacao> findByFuncionarioIdOrderByDataAvaliacaoDesc(Long id);

    @Query("SELECT AVG(a.nota) FROM Avaliacao a WHERE :funcionario = funcionario")
    BigDecimal notaMedia(@Param("funcionario") Funcionario funcionario);
}
