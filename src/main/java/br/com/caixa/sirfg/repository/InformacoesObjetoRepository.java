package br.com.caixa.sirfg.repository;

import br.com.caixa.sirfg.model.InformacaoSp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformacoesObjetoRepository extends JpaRepository<InformacaoSp, Long> {
    List<InformacaoSp> findAllByObjetoId(Long id);
}