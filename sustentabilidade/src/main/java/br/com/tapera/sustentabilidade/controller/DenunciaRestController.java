package br.com.tapera.sustentabilidade.controller;

import br.com.tapera.sustentabilidade.model.Denuncia;
import br.com.tapera.sustentabilidade.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/denuncias")
@CrossOrigin(origins = "*")
public class DenunciaRestController {

    @Autowired
    private DenunciaService denunciaService;

    @GetMapping
    public ResponseEntity<List<Denuncia>> listarTodas() { return ResponseEntity.ok(denunciaService.buscarTodas()); }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Map<String, Object> payload) {
        try {
            Denuncia d = new Denuncia();
            d.setTitulo((String) payload.get("titulo"));
            d.setTipo((String) payload.get("tipo"));
            d.setDescricao((String) payload.get("descricao"));
            d.setLocalizacao((String) payload.get("localizacao"));
            d.setReferencia((String) payload.get("referencia"));

            if (payload.containsKey("coordenadas")) {
                Map<?, ?> coords = (Map<?, ?>) payload.get("coordenadas");
                d.setLat(Double.parseDouble(coords.get("lat").toString()));
                d.setLng(Double.parseDouble(coords.get("lng").toString()));
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(denunciaService.salvar(d));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Erro ao processar dados geoespaciais"));
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(denunciaService.atualizarStatus(id, body.get("status")));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        return denunciaService.excluir(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
