package rh.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "avaliacoes")
public class Avaliacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idFuncionario;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @Column(nullable = false, length = 30)
    private String avaliador;

    @Column(nullable = false)
    private BigDecimal nota;

    @Column(nullable = false, length = 50)
    private String comentario;

    @Column(nullable = false)
    private LocalDateTime dataAvaliacao;

    @PrePersist
    private void prePersist(){
        this.dataAvaliacao = LocalDateTime.now();
    }

}
