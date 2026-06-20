package br.com.tapera.sustentabilidade.repository;

import br.com.tapera.sustentabilidade.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {

    // Consulta para contar ocorrências por tipo
    @Query("SELECT d.tipo, COUNT(d) FROM Denuncia d GROUP BY d.tipo")
    List<Object[]> countDenunciasPorTipo();
}