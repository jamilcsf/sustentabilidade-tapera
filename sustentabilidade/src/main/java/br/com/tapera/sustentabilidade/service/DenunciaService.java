package br.com.tapera.sustentabilidade.service;

import br.com.tapera.sustentabilidade.model.Denuncia;
import br.com.tapera.sustentabilidade.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    public List<Denuncia> buscarTodas() { return denunciaRepository.findAll(); }
    public Denuncia buscarPorId(Long id) { return denunciaRepository.findById(id).orElse(null); }
    public Denuncia salvar(Denuncia d) { return denunciaRepository.save(d); }
    public boolean excluir(Long id) {
        if (denunciaRepository.existsById(id)) {
            denunciaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Denuncia atualizarStatus(Long id, String status) {
        Denuncia d = buscarPorId(id);
        if (d != null) {
            d.setStatus(status);
            return denunciaRepository.save(d);
        }
        throw new RuntimeException("Denúncia não encontrada");
    }

    public Map<String, Object> calcularEstatisticas() {
        List<Denuncia> todas = denunciaRepository.findAll();
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("total", todas.size());

        Map<String, Long> porStatus = todas.stream()
                .collect(Collectors.groupingBy(Denuncia::getStatus, Collectors.counting()));
        resultado.put("porStatus", porStatus);

        Map<String, Long> porTipo = todas.stream()
                .collect(Collectors.groupingBy(Denuncia::getTipo, Collectors.counting()));
        resultado.put("porTipo", porTipo);

        return resultado;
    }
}
