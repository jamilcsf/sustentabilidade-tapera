package br.com.tapera.sustentabilidade.controller;

import br.com.tapera.sustentabilidade.dto.DenunciaRequestDTO;
import br.com.tapera.sustentabilidade.model.Denuncia;
import br.com.tapera.sustentabilidade.service.DenunciaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/denuncias")
@CrossOrigin(origins = "*") // Importante para o front-end acessar
public class DenunciaController {

    private final DenunciaService service;

    public DenunciaController(DenunciaService service) {
        this.service = service;
    }

    // LISTAR todas as denúncias
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    // CRIAR uma nova denúncia (com validação DTO)
    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody DenunciaRequestDTO dto) {
        Denuncia salva = service.salvar(dto);
        return ResponseEntity.ok(salva);
    }

    // ATUALIZAR STATUS (Endpoint para o ciclo de vida da denúncia)
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String novoStatus = payload.get("status");

        if (novoStatus == null || novoStatus.isBlank()) {
            return ResponseEntity.badRequest().body("O campo 'status' é obrigatório.");
        }

        Denuncia atualizada = service.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(atualizada);
    }
}