package br.com.tapera.sustentabilidade.security;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

    // Gerando chave segura para HS256
    private final SecretKey key = Jwts.SIG.HS256.key().build();

    public String gerarToken(String email) {
        return Jwts.builder()
                .subject(email) // Mudou de .setSubject() para .subject()
                .issuedAt(new Date()) // Mudou de .setIssuedAt() para .issuedAt()
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Mudou para .expiration()
                .signWith(key)
                .compact();
    }

    public String extrairEmail(String token) {
        return Jwts.parser()
                .verifyWith(key) // Mudou de .setSigningKey() para .verifyWith()
                .build()
                .parseSignedClaims(token) // Mudou de .parseClaimsJws() para .parseSignedClaims()
                .getPayload() // Mudou de .getBody() para .getPayload()
                .getSubject();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}