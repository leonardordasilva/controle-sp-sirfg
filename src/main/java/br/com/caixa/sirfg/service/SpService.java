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
public class SpService {
    private final SpRepository spRepository;

    private final InformacoesObjetoRepository objetoRepository;

    public SpService(SpRepository spRepository, InformacoesObjetoRepository objetoRepository) {
        this.spRepository = spRepository;
        this.objetoRepository = objetoRepository;
    }

    public void createOrUpdate(Sp sp) {
        spRepository.save(sp);
    }

    public List<Sp> findAll() {
        return spRepository.findAll();
    }

    public Optional<Sp> findById(Long id) {
        return spRepository.findById(id);
    }

    public List<Sp> findAllByNome(String nomeObejto) {
        return spRepository.findAllByNome(nomeObejto);
    }

    public List<Sp> findAllDesTqs() {
        return spRepository.findAllDesTqs();
    }

    public List<Sp> findAllTqsHmp() {
        return spRepository.findAllTqsHmp();
    }

    public List<Sp> findAllHmpPrd() {
        return spRepository.findAllHmpPrd();
    }

    public void delete(Long Id) {
        spRepository.deleteById(Id);
    }

    public List<Sp> agruparSps(List<Sp> spRetorno, List<Sp> sps, TipoObjetoEnum tipoObjeto) {
        spRetorno.clear();

        for (Sp sp : sps) {
            if (sp.getTipoObjeto() == tipoObjeto) {
                spRetorno.add(sp);
            }
        }

        return spRetorno;
    }

    public List<InformacaoSp> obterHistoricoObjeto(Long id) {
        return objetoRepository.findAllByObjetoId(id);
    }
}