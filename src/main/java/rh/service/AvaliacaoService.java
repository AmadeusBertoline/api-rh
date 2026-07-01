package rh.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import rh.dto.AvaliacaoRequestDTO;
import rh.dto.AvaliacaoResponseDTO;
import rh.exception.RegraNegocioException;
import rh.exception.ResourceNotFoundException;
import rh.model.Avaliacao;
import rh.model.Funcionario;
import rh.repository.AvaliacaoRepository;
import rh.repository.FuncionarioRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @Transactional
    public AvaliacaoResponseDTO criar(AvaliacaoRequestDTO dto){

        Funcionario funcionario = funcionarioRepository.findById(dto.getIdFuncionario())
        .orElseThrow(() -> new RegraNegocioException("O funcionário selecionado não existe"));
        
        if(dto.getNota().compareTo(BigDecimal.ZERO) <= 0 || dto.getNota().compareTo(BigDecimal.TEN) > 0){
            throw new RegraNegocioException("A nota deve ser entre 1 e 10");
        }

        if(!funcionario.getAtivo()){
            throw new RegraNegocioException("Funcionário inativos não podem ser avaliados");
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setIdFuncionario(dto.getIdFuncionario());
        avaliacao.setFuncionario(funcionario);
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

    public AvaliacaoResponseDTO avaliacaoPorId(Long id){

        Avaliacao avaliacao = avaliacaoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Avaliação não encontrada de id: "+id));

        return toDTO(avaliacao);

    }

    public BigDecimal notaMedia(Long id){

        return avaliacaoRepository.notaMedia(id);

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
