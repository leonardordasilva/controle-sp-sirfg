package br.com.caixa.sirfg.repository;

import br.com.caixa.sirfg.model.Sp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpRepository extends JpaRepository<Sp, Long> {
    @Query("SELECT sp FROM Sp sp WHERE sp.dataDes <> sp.dataTqs OR (sp.dataDes is not null and sp.dataTqs is null)")
    List<Sp> findAllDesTqs();

    @Query("SELECT sp FROM Sp sp WHERE sp.dataTqs <> sp.dataHmp OR (sp.dataTqs is not null and sp.dataHmp is null)")
    List<Sp> findAllTqsHmp();

    @Query("SELECT sp FROM Sp sp WHERE sp.dataHmp <> sp.dataPrd OR (sp.dataHmp is not null and sp.dataPrd is null)")
    List<Sp> findAllHmpPrd();
}