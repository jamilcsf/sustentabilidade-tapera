package br.com.tapera.sustentabilidade.dto;

import br.com.tapera.sustentabilidade.model.Denuncia;

public record DenunciaResponseDTO(
        Long id,
        String titulo,
        String tipo,
        String descricao,
        String localizacao,
        String referencia,
        double lat,
        double lng,
        String status
) {
    // Construtor que recebe a entidade e converte automaticamente
    public DenunciaResponseDTO(Denuncia d) {
        this(d.getId(), d.getTitulo(), d.getTipo(), d.getDescricao(),
                d.getLocalizacao(), d.getReferencia(), d.getLat(), d.getLng(), d.getStatus());
    }
}