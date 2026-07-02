package rh.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AvaliacaoRequestDTO {
    
    @NotNull(message = "O id do funcionário é obrigatório")
    private Long idFuncionario;

    @NotBlank(message = "O nome do avaliador é obrigatório")
    private String avaliador;

    @Positive(message = "A nota deve ser maior que zero")
    private BigDecimal nota;

    @NotBlank(message = "O comentário é obrigatório")
    private String comentario;
    
}
