package rh.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rh.dto.FuncionarioRequestDTO;
import rh.dto.FuncionarioResponseDTO;
import rh.exception.ResourceNotFoundException;
import rh.model.Funcionario;
import rh.repository.AvaliacaoRepository;
import rh.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Transactional
    public FuncionarioResponseDTO criar(FuncionarioRequestDTO dto) {

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());
        funcionario.setCpf(dto.getCpf());
        funcionario.setCargo(dto.getCargo());
        funcionario.setSalario(dto.getSalario());
        funcionario.setDepartamento(dto.getDepartamento());

        Funcionario cadastrado = funcionarioRepository.save(funcionario);

        return toDTO(cadastrado);

    }

    public List<FuncionarioResponseDTO> listar(String departamento) {

        return (
            funcionarioRepository.buscarPorDepartamentoOpcional(departamento)
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList())
        );

    }

    @Transactional
    public FuncionarioResponseDTO atualizar(Long id, FuncionarioRequestDTO dto) {

        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado de id: " + id));

        funcionario.setId(id);
        funcionario.setNome(dto.getNome());
        funcionario.setSalario(dto.getSalario());
        funcionario.setCargo(dto.getCargo());
        funcionario.setCpf(dto.getCpf());
        funcionario.setDepartamento(dto.getDepartamento());

        funcionarioRepository.save(funcionario);

        return toDTO(funcionario);

    }

    @Transactional
    public void deletar(Long id) {

        funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado de id: " + id));

        funcionarioRepository.deleteById(id);

    }

    public FuncionarioResponseDTO buscaPorId(Long id) {

        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado de id: " + id));

        return toDTO(funcionario); 

    }

    @Transactional
    public FuncionarioResponseDTO desativar(Long id){

        Funcionario funcionario = funcionarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado de id: "+id));

        funcionario.setId(id);
        funcionario.setAtivo(false);

        funcionarioRepository.save(funcionario);

        return toDTO(funcionario);

    }

    @Transactional
    public FuncionarioResponseDTO ativar(Long id){

        Funcionario funcionario = funcionarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado de id: "+id));

        funcionario.setId(id);
        funcionario.setAtivo(true);

        funcionarioRepository.save(funcionario);

        return toDTO(funcionario);

    }

    public BigDecimal notaMedia(Long id){

        Funcionario funcionario = funcionarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado de id: "+id));

        return avaliacaoRepository.notaMedia(funcionario);

    }
    
    private FuncionarioResponseDTO toDTO(Funcionario funcionario) {

        FuncionarioResponseDTO dto = new FuncionarioResponseDTO();
        dto.setId(funcionario.getId());
        dto.setNome(funcionario.getNome());
        dto.setCpf(funcionario.getCpf());
        dto.setCargo(funcionario.getCargo());
        dto.setDepartamento(funcionario.getDepartamento());
        dto.setSalario(funcionario.getSalario());
        dto.setDataAdmissao(funcionario.getDataAdmissao());
        dto.setDataAdmissao(funcionario.getDataAdmissao());
        dto.setAtivo(funcionario.getAtivo());
        
        return dto;

    }
}