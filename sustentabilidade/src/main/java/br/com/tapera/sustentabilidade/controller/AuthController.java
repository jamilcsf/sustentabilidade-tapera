package br.com.tapera.sustentabilidade.controller;

import br.com.tapera.sustentabilidade.model.Usuario;
import br.com.tapera.sustentabilidade.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios")
    public ResponseEntity<?> cadastrar(@RequestBody Usuario usuario) {
        try {
            Usuario salvo = usuarioService.cadastrar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        }
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciais) {
        try {
            Usuario logado = usuarioService.autenticar(credenciais.get("email"), credenciais.get("senha"));
            // Mock de Token para satisfazer o Front-end sem complexidade do Spring Security tradicional
            return ResponseEntity.ok(Map.of(
                    "token", "mocked-jwt-token-for-senai-project",
                    "id", logado.getId(),
                    "nome", logado.getNome(),
                    "email", logado.getEmail()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("erro", e.getMessage()));
        }
    }
}
