package rh.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class AvaliacaoRequestDTO {
    
    private Long idFuncionario;
    private String avaliador;
    private BigDecimal nota;
    private String comentario;
    
}
