package rh.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, length = 60)
    private String cargo;

    @Column(nullable = false)
    private BigDecimal salario;

    @Column(nullable = false, length = 60)
    private String departamento;

    @Column(nullable = false)
    private LocalDateTime dataAdmissao;

    @Column(nullable = false)
    private Boolean ativo;

    @PrePersist
    private void prePersist(){

        this.dataAdmissao = LocalDateTime.now();
        this.ativo = true;
        
    }
    
}
