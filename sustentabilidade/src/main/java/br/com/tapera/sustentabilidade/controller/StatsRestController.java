package br.com.tapera.sustentabilidade.controller;

import br.com.tapera.sustentabilidade.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/stats")
public class StatsRestController {

    @Autowired
    private DenunciaService denunciaService;

    @GetMapping
    public ResponseEntity<Map<String, Long>> getEstatisticas() {
        return ResponseEntity.ok(denunciaService.calcularEstatisticas());
    }
}