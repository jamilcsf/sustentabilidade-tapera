package br.com.tapera.sustentabilidade.security;

import org.springframework.stereotype.Component;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    // Injeção via construtor (Padrão de mercado exigido pelo professor)
    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Busca o cabeçalho Authorization
        String authHeader = request.getHeader("Authorization");

        // 2. Verifica se existe o token e se começa com "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove o prefixo "Bearer "

            // 3. Extrai o e-mail do token
            String email = jwtService.extrairEmail(token);

            // 4. Se o e-mail é válido e a autenticação ainda não foi definida no contexto
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // Cria a autenticação no contexto do Spring Security
                var auth = new UsernamePasswordAuthenticationToken(email, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        // 5. Segue a cadeia de filtros
        filterChain.doFilter(request, response);
    }
}