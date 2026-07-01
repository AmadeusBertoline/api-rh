package rh.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class AvaliacaoRequestDTO {
    
    @NotNull(message = "O id do funcionário é obrigatório")
    private Long idFuncionario;

    @NotBlank(message = "O nome do avaliador é obrigatório")
    private String avaliador;

    @PositiveOrZero(message = "A nota deve ser maior ou igual a zero")
    private BigDecimal nota;

    @NotBlank(message = "O comentário é obrigatório")
    private String comentario;
    
}
