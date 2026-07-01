package rh.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FuncionarioRequestDTO {
    

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O cpf é obrigatório")
    @Size(min = 11, max = 11, message = "O cpf deve ser digitado sem pontuação")
    private String cpf;

    @NotBlank(message = "O cargo é obrigatório")
    private String cargo;

    @Positive(message = "O número deve ser positivo")
    private BigDecimal salario;

    @NotBlank(message = "O departamento é obrigatório")
    private String departamento;
    
}
