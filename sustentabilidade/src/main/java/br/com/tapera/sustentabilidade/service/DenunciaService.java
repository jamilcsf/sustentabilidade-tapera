package br.com.tapera.sustentabilidade.service;

import br.com.tapera.sustentabilidade.dto.DenunciaRequestDTO;
import br.com.tapera.sustentabilidade.model.Denuncia;
import br.com.tapera.sustentabilidade.repository.DenunciaRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DenunciaService {

    private final DenunciaRepository repository;

    // Coordenadas aproximadas do Bairro Tapera, Florianópolis
    private static final double TAPERA_LAT = -27.6810;
    private static final double TAPERA_LNG = -48.5620;
    private static final double RAIO_MAXIMO_KM = 3.0; // Limite de 3km

    public DenunciaService(DenunciaRepository repository) {
        this.repository = repository;
    }

    public Denuncia salvar(DenunciaRequestDTO dto) {
        if (!estaDentroDoBairro(dto.lat(), dto.lng())) {
            throw new IllegalArgumentException("A denúncia deve estar localizada no bairro Tapera.");
        }

        Denuncia denuncia = new Denuncia();
        denuncia.setTitulo(dto.titulo());
        denuncia.setTipo(dto.tipo());
        denuncia.setDescricao(dto.descricao());
        denuncia.setLocalizacao(dto.localizacao());
        denuncia.setReferencia(dto.referencia());
        denuncia.setLat(dto.lat());
        denuncia.setLng(dto.lng());

        return repository.save(denuncia);
    }

    public List<Denuncia> listarTodas() {
        return repository.findAll();
    }

    // MÉTODOS DE CICLO DE VIDA (Para o PATCH)
    public Denuncia atualizarStatus(Long id, String novoStatus) {
        Denuncia denuncia = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Denúncia não encontrada com ID: " + id));

        denuncia.setStatus(novoStatus);
        return repository.save(denuncia);
    }

    // MÉTODO OTIMIZADO USANDO O REPOSITORY
    public Map<String, Long> calcularEstatisticas() {
        List<Object[]> resultados = repository.countDenunciasPorTipo();
        Map<String, Long> estatisticas = new HashMap<>();

        // Inicializa chaves para garantir que o JSON retorne valores, mesmo que sejam 0
        estatisticas.put("Total", repository.count());
        estatisticas.put("Vazamento de Água", 0L);
        estatisticas.put("Esgoto a Céu Aberto", 0L);
        estatisticas.put("Falta de Água", 0L);
        estatisticas.put("Outros", 0L);

        // Preenche com os dados reais agrupados pelo banco de dados
        for (Object[] obj : resultados) {
            String tipo = (String) obj[0];
            Long contagem = (Long) obj[1];
            estatisticas.put(tipo, contagem);
        }

        return estatisticas;
    }

    private boolean estaDentroDoBairro(double lat, double lng) {
        return calcularDistancia(TAPERA_LAT, TAPERA_LNG, lat, lng) <= RAIO_MAXIMO_KM;
    }

    private double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Raio da Terra em km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}