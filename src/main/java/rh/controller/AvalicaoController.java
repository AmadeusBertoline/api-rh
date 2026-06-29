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

import rh.dto.AvaliacaoRequestDTO;
import rh.dto.AvaliacaoResponseDTO;
import rh.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacoes")
public class AvalicaoController {
    
    @Autowired
    private AvaliacaoService avaliacaoService;
    
    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> criar(@RequestBody AvaliacaoRequestDTO dto){

        AvaliacaoResponseDTO avaliacao = avaliacaoService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacao);

    }

    @GetMapping("/funcionario/{id}")
    public ResponseEntity<List<AvaliacaoResponseDTO>> avaliacoesPorFuncionario(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(avaliacaoService.avaliacoesPorFuncionario(id));

    }

}
