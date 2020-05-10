package br.com.caixa.sirfg.repository;

import br.com.caixa.sirfg.model.Sp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpRepository extends JpaRepository<Sp, Long> {
}