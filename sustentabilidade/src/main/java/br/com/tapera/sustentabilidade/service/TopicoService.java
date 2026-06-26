package br.com.tapera.sustentabilidade.service;

import br.com.tapera.sustentabilidade.dto.TopicoRequestDTO;
import br.com.tapera.sustentabilidade.dto.TopicoResponseDTO; // Importe o DTO
import br.com.tapera.sustentabilidade.model.Topico;
import br.com.tapera.sustentabilidade.model.Usuario;
import br.com.tapera.sustentabilidade.repository.TopicoRepository;
import br.com.tapera.sustentabilidade.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    public TopicoService(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public TopicoResponseDTO criar(TopicoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));

        Topico topico = new Topico();
        topico.setTitulo(dto.titulo());
        topico.setConteudo(dto.conteudo());
        topico.setUsuario(usuario);

        Topico salvo = topicoRepository.save(topico);

        // Retorna o DTO em vez da Entidade
        return new TopicoResponseDTO(salvo.getId(), salvo.getTitulo(), salvo.getConteudo(), salvo.getUsuario().getNome());
    }

    public List<TopicoResponseDTO> listarTodos() {
        return topicoRepository.findAll().stream()
                .map(t -> new TopicoResponseDTO(t.getId(), t.getTitulo(), t.getConteudo(), t.getUsuario().getNome()))
                .toList();
    }
}