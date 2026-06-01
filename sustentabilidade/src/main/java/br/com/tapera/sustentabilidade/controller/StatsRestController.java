package br.com.tapera.sustentabilidade.controller;

import br.com.tapera.sustentabilidade.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin(origins = "*")
public class StatsRestController {

    @Autowired
    private DenunciaService denunciaService;

    @GetMapping
    public ResponseEntity<?> obterEstatisticas() { return ResponseEntity.ok(denunciaService.calcularEstatisticas()); }
}
