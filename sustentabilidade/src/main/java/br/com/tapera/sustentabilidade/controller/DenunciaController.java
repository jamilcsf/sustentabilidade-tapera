package br.com.tapera.sustentabilidade.controller;

import br.com.tapera.sustentabilidade.dto.DenunciaRequestDTO;
import br.com.tapera.sustentabilidade.dto.DenunciaResponseDTO;
import br.com.tapera.sustentabilidade.dto.DenunciaUpdateDTO;
import br.com.tapera.sustentabilidade.service.DenunciaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/denuncias")
@CrossOrigin(origins = "*")
public class DenunciaController {

    private final DenunciaService service;

    // Injeção via construtor (Obrigatório para o Padrão Ouro)
    public DenunciaController(DenunciaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DenunciaResponseDTO> criar(
            @RequestBody @Valid DenunciaRequestDTO dto,
            UriComponentsBuilder uriBuilder) {

        DenunciaResponseDTO response = service.salvar(dto);

        // Criar a URI do novo recurso (Padrão RESTful)
        URI uri = uriBuilder.path("/api/denuncias/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DenunciaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DenunciaResponseDTO> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DenunciaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DenunciaUpdateDTO dto) {

        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}