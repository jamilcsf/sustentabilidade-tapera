package br.com.tapera.sustentabilidade.controller;

import br.com.tapera.sustentabilidade.dto.TopicoRequestDTO;
import br.com.tapera.sustentabilidade.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topicos")
@CrossOrigin(origins = "*")
public class TopicoRestController {

    private final TopicoService topicoService;

    // Injeção via construtor (Padrão Ouro)
    public TopicoRestController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return ResponseEntity.ok(topicoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody TopicoRequestDTO dto) {
        try {
            // O Service agora deve encapsular a lógica de buscar o usuário e salvar o tópico
            return ResponseEntity.ok(topicoService.criar(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}