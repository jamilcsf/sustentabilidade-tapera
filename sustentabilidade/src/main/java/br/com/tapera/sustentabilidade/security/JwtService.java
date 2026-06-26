package br.com.tapera.sustentabilidade.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // Chave secreta gerada automaticamente para assinar o token.
    // Em produção, isso deveria estar no application.properties
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Método para gerar o Token JWT
    public String gerarToken(String email) {
        return Jwts.builder()
                .setSubject(email) // O "dono" do token será o e-mail
                .setIssuedAt(new Date()) // Data de emissão
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Expira em 1 hora
                .signWith(key) // Assina com a nossa chave secreta
                .compact();
    }

    // Método para extrair o e-mail de dentro do token (usado no Filtro)
    public String extrairEmail(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Método para validar se o token ainda é válido
    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}