package br.com.tapera.sustentabilidade.service;

import br.com.tapera.sustentabilidade.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class UsuarioDetalheService implements UserDetailsService {

    private final UsuarioRepository repository;

    public UsuarioDetalheService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var usuario = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        return new User(usuario.getEmail(), usuario.getPassword(), Collections.emptyList());
    }
}
