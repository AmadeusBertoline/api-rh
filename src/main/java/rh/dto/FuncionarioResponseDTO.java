package rh.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FuncionarioResponseDTO {
    
    private Long id;
    private String nome;
    private String cpf;
    private String cargo;
    private BigDecimal salario;
    private String departamento;
    private LocalDateTime dataAdmissao;
    private Boolean ativo;

}
