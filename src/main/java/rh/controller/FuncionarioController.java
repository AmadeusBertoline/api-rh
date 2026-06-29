package rh.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rh.dto.FuncionarioRequestDTO;
import rh.dto.FuncionarioResponseDTO;
import rh.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> criar(@RequestBody FuncionarioRequestDTO dto){

        FuncionarioResponseDTO funcionario = funcionarioService.criar(dto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);

    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listarTodos(){
        
       List<FuncionarioResponseDTO> funcionarios = funcionarioService.listarTodos();
       return ResponseEntity.status(HttpStatus.OK).body(funcionarios);

    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(@RequestBody FuncionarioRequestDTO dto, @PathVariable Long id){

        FuncionarioResponseDTO atualizado = funcionarioService.atualizar(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(atualizado);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> deletar(@PathVariable Long id){

        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscaPorId(@PathVariable Long id){

        FuncionarioResponseDTO funcionario = funcionarioService.buscaPorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(funcionario);

    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<FuncionarioResponseDTO> desativar(@PathVariable Long id){

        FuncionarioResponseDTO funcionario = funcionarioService.desativar(id);

        return ResponseEntity.status(HttpStatus.OK).body(funcionario);

    }

    @PutMapping("/{id}/ativar")
    public ResponseEntity<FuncionarioResponseDTO> ativar(@PathVariable Long id){

        FuncionarioResponseDTO funcionario = funcionarioService.ativar(id);

        return ResponseEntity.status(HttpStatus.OK).body(funcionario);

    }

}
