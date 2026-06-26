package rh.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FuncionarioRequestDTO {
    
    private Long id;
    private String nome;
    private String cpf;
    private String cargo;
    private BigDecimal salario;
    private String departamento;
    
}
