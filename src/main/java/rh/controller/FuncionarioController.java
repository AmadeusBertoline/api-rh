package rh.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import rh.dto.FuncionarioRequestDTO;
import rh.dto.FuncionarioResponseDTO;
import rh.service.FuncionarioService;

@Tag(name = "funcionarios")
@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioService funcionarioService;

    @Operation(summary = "criar um novo funcionário")
    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> criar(@RequestBody @Valid FuncionarioRequestDTO dto){

        FuncionarioResponseDTO funcionario = funcionarioService.criar(dto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);

    }

    @Operation(summary = "lista os funcionários por departamento ou não")
    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listar(@RequestParam(value = "departamento", required = false) String departamento){
        
       List<FuncionarioResponseDTO> funcionarios = funcionarioService.listar(departamento);
       return ResponseEntity.status(HttpStatus.OK).body(funcionarios);

    }

    @Operation(summary = "atualizar os dados de um funcionário")
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(@RequestBody @Valid FuncionarioRequestDTO dto, @PathVariable @Valid Long id){

        FuncionarioResponseDTO atualizado = funcionarioService.atualizar(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(atualizado);

    }

    @Operation(summary = "deletar um funcionário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable @Valid Long id){

        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();

    }

    @Operation(summary = "buscar um funcionário pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscaPorId(@PathVariable Long id){

        FuncionarioResponseDTO funcionario = funcionarioService.buscaPorId(id);
        funcionario.setNotaMedia(funcionarioService.notaMedia(id));

        return ResponseEntity.status(HttpStatus.OK).body(funcionario);

    }

    @Operation(summary = "desativar um funcionário")
    @PatchMapping("/{id}/desativar")
    public ResponseEntity<FuncionarioResponseDTO> desativar(@PathVariable @Valid Long id){

        FuncionarioResponseDTO funcionario = funcionarioService.desativar(id);

        return ResponseEntity.status(HttpStatus.OK).body(funcionario);

    }

    @Operation(summary = "ativar um funcionário")
    @PatchMapping("/{id}/ativar")
    public ResponseEntity<FuncionarioResponseDTO> ativar(@PathVariable @Valid Long id){

        FuncionarioResponseDTO funcionario = funcionarioService.ativar(id);

        return ResponseEntity.status(HttpStatus.OK).body(funcionario);

    }

}
