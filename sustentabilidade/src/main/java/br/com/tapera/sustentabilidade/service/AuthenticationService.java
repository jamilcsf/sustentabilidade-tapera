package br.com.tapera.sustentabilidade.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.tapera.sustentabilidade.dto.AutenticacaoDTO;
import br.com.tapera.sustentabilidade.security.JwtService; // Importe o JwtService que criamos

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService; // Nova dependência

    // Injeção via construtor (Padrão Profissional)
    public AuthenticationService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String autenticar(AutenticacaoDTO dto) {
        // 1. Cria o token de autenticação
        var authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());

        // 2. O Spring Security valida as credenciais contra o banco
        Authentication auth = authenticationManager.authenticate(authToken);

        // 3. Se autenticado, gera o token JWT usando o email (que no Spring Security é
        // o 'principal')
        return jwtService.gerarToken(auth.getName());
    }
}