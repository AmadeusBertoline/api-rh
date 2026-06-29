package rh.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rh.dto.AvaliacaoRequestDTO;
import rh.dto.AvaliacaoResponseDTO;
import rh.exception.ResourceNotFoundException;
import rh.model.Avaliacao;
import rh.repository.AvaliacaoRepository;
import rh.repository.FuncionarioRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    public AvaliacaoResponseDTO criar(AvaliacaoRequestDTO dto){

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdFuncionario(dto.getIdFuncionario());
        avaliacao.setAvaliador(dto.getAvaliador());
        avaliacao.setNota(dto.getNota());
        avaliacao.setComentario(dto.getComentario());

        Avaliacao avaliado = avaliacaoRepository.save(avaliacao);

        return toDTO(avaliado);

    }

    public List<AvaliacaoResponseDTO>avaliacoesPorFuncionario(Long id){

        funcionarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado de id: " +id));

        return avaliacaoRepository.findByIdFuncionarioOrderByIdDesc(id)
        .stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
        
    }

    private AvaliacaoResponseDTO toDTO(Avaliacao avaliacao){

        AvaliacaoResponseDTO dto = new AvaliacaoResponseDTO();
        dto.setId(avaliacao.getId());
        dto.setIdFuncionario(avaliacao.getIdFuncionario());
        dto.setAvaliador(avaliacao.getAvaliador());
        dto.setNota(avaliacao.getNota());
        dto.setDataAvaliacao(avaliacao.getDataAvaliacao());
        dto.setComentario(avaliacao.getComentario());

        return dto;

    }

}
