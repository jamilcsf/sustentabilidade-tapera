package br.com.tapera.sustentabilidade.service;

import br.com.tapera.sustentabilidade.model.Topico;
import br.com.tapera.sustentabilidade.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public List<Topico> listarTodos() { return topicoRepository.findAll(); }
    public Topico criar(Topico topico) { return topicoRepository.save(topico); }

    public Topico votar(Long id, String tipo) {
        Topico t = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        if ("like".equalsIgnoreCase(tipo)) {
            t.setLikes(t.getLikes() + 1);
        } else if ("deslike".equalsIgnoreCase(tipo)) {
            t.setDeslikes(t.getDeslikes() + 1);
        }
        return topicoRepository.save(t);
    }
}