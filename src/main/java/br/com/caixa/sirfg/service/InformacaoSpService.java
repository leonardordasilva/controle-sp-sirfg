package br.com.caixa.sirfg.service;

import br.com.caixa.sirfg.model.InformacaoSp;
import br.com.caixa.sirfg.model.Sp;
import br.com.caixa.sirfg.model.enumerator.TipoObjetoEnum;
import br.com.caixa.sirfg.repository.InformacoesObjetoRepository;
import br.com.caixa.sirfg.repository.SpRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformacaoSpService {
    private final InformacoesObjetoRepository objetoRepository;

    public InformacaoSpService(InformacoesObjetoRepository objetoRepository) {
        this.objetoRepository = objetoRepository;
    }

    public List<InformacaoSp> obterHistoricoObjeto(Long id) {
        return objetoRepository.findAllByObjetoId(id);
    }
}