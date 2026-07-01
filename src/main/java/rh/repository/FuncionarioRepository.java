package rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rh.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

    @Query("SELECT f FROM Funcionario f WHERE " +
           "(:departamento IS NULL OR f.departamento = :departamento)")
    List<Funcionario> buscarPorDepartamentoOpcional(@Param("departamento") String departamento);
    
}
