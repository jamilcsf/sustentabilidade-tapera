package br.com.tapera.sustentabilidade.controller;

import br.com.tapera.sustentabilidade.dto.AutenticacaoDTO;
import br.com.tapera.sustentabilidade.dto.UsuarioRequestDTO;
import br.com.tapera.sustentabilidade.dto.UsuarioResponseDTO;
import br.com.tapera.sustentabilidade.service.AuthenticationService;
import br.com.tapera.sustentabilidade.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    // Injeção via construtor: o padrão exigido pelo professor
    private final UsuarioService usuarioService;
    private final AuthenticationService authenticationService;

    public AuthController(UsuarioService usuarioService, AuthenticationService authenticationService) {
        this.usuarioService = usuarioService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> cadastrar(@RequestBody UsuarioRequestDTO dto) {
        try {
            UsuarioResponseDTO salvo = usuarioService.cadastrar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AutenticacaoDTO dto) {
        try {
            // Agora delegamos para o AuthenticationService, que usará o JWT
            String token = authenticationService.autenticar(dto);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("erro", e.getMessage()));
        }
    }
}