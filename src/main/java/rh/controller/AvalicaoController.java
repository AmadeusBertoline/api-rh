package rh.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import rh.dto.AvaliacaoRequestDTO;
import rh.dto.AvaliacaoResponseDTO;
import rh.service.AvaliacaoService;

@Tag(name = "avaliacoes")
@RestController
@RequestMapping("/avaliacoes")
public class AvalicaoController {
    
    @Autowired
    private AvaliacaoService avaliacaoService;
    
    @Operation(summary = "criar avaliação")
    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> criar(@RequestBody @Valid AvaliacaoRequestDTO dto){

        AvaliacaoResponseDTO avaliacao = avaliacaoService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacao);

    }

    @Operation(summary = "listar as avaliações de um funcionário")
    @GetMapping("/funcionario/{id}")
    public ResponseEntity<List<AvaliacaoResponseDTO>> avaliacoesPorFuncionario(@PathVariable @Valid Long id){

        return ResponseEntity.status(HttpStatus.OK).body(avaliacaoService.avaliacoesPorFuncionario(id));

    }

    @Operation(summary = "buscar uma avaliação por id")
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> avaliacaoPorId(@PathVariable @Valid Long id){

        AvaliacaoResponseDTO avaliacao = avaliacaoService.avaliacaoPorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(avaliacao);

    }

}
