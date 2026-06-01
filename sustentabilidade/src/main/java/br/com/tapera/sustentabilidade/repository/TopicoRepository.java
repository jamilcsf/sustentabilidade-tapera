package br.com.tapera.sustentabilidade.repository;

import br.com.tapera.sustentabilidade.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
