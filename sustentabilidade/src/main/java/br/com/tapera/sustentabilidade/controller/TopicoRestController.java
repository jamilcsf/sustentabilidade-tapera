package br.com.tapera.sustentabilidade.controller;

import br.com.tapera.sustentabilidade.dto.TopicoRequestDTO;
import br.com.tapera.sustentabilidade.model.Topico;
import br.com.tapera.sustentabilidade.model.Usuario;
import br.com.tapera.sustentabilidade.service.TopicoService;
import br.com.tapera.sustentabilidade.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/topicos")
@CrossOrigin(origins = "*")
public class TopicoRestController {

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Topico>> listarTodos() {
        return ResponseEntity.ok(topicoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody TopicoRequestDTO dto) {
        // Busca o usuário no banco
        Usuario u = usuarioService.buscarPorId(dto.usuarioId());
        if (u == null) {
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }

        // Cria o tópico
        Topico t = new Topico();
        t.setTitulo(dto.titulo());
        t.setConteudo(dto.conteudo());
        t.setUsuario(u);

        return ResponseEntity.ok(topicoService.criar(t));
    }
}