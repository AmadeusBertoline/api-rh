package rh.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;


@Data
public class AvaliacaoResponseDTO {
    
    private Long id;
    private Long idFuncionario;
    private String avaliador;
    private BigDecimal nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;

}
