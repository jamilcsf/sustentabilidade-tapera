package br.com.tapera.sustentabilidade.controller;

import br.com.tapera.sustentabilidade.model.Topico;
import br.com.tapera.sustentabilidade.model.Usuario;
import br.com.tapera.sustentabilidade.service.TopicoService;
import br.com.tapera.sustentabilidade.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/topicos")
@CrossOrigin(origins = "*")
public class TopicoRestController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Topico>> listarTodos() { return ResponseEntity.ok(topicoService.listarTodos()); }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Map<String, Object> payload) {
        try {
            Topico t = new Topico();
            t.setTitulo((String) payload.get("titulo"));
            t.setConteudo((String) payload.get("conteudo"));

            Long usuarioId = Long.parseLong(payload.get("usuarioId").toString());
            Usuario u = usuarioService.buscarPorId(usuarioId);
            t.setUsuario(u);

            return ResponseEntity.ok(topicoService.criar(t));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Erro ao criar tópico"));
        }
    }

    @PostMapping("/{id}/voto")
    public ResponseEntity<?> votar(@PathVariable Long id, @RequestParam String tipo) {
        try {
            return ResponseEntity.ok(topicoService.votar(id, tipo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }
}