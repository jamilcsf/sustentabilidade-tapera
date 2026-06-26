package br.com.tapera.sustentabilidade.service;

import br.com.tapera.sustentabilidade.dto.UsuarioRequestDTO;
import br.com.tapera.sustentabilidade.dto.UsuarioResponseDTO;
import br.com.tapera.sustentabilidade.model.Usuario;
import br.com.tapera.sustentabilidade.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    // 1. Campos marcados como 'final' garantem a imutabilidade
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // 2. Injeção de Dependência via Construtor (Boa prática do Spring)
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 3. Recebe um DTO e devolve um DTO (Segurança de Encapsulamento)
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        if(usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado!"); // Exceção mais específica
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());

        // 4. Segurança: Criptografando a senha antes de salvar no banco
        usuario.setSenha(passwordEncoder.encode(dto.senha()));

        Usuario salvo = usuarioRepository.save(usuario);

        // 5. Retorna o DTO sem a senha, protegendo a API
        return new UsuarioResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
    }

    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}