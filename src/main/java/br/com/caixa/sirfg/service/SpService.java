package br.com.caixa.sirfg.service;

import br.com.caixa.sirfg.model.Sp;
import br.com.caixa.sirfg.model.TipoObjetoEnum;
import br.com.caixa.sirfg.repository.SpRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpService {
    private final SpRepository spRepository;

    public SpService(SpRepository spRepository) {
        this.spRepository = spRepository;
    }

    public void create(Sp sp) {
        spRepository.save(sp);
    }

    public List<Sp> findAll() {
        return spRepository.findAll();
    }

    public Optional<Sp> findById(Long Id) {
        return spRepository.findById(Id);
    }

    public List<Sp> findAllByNome(String nomeObejto) {
        return spRepository.findAllByNome(nomeObejto);
    }

    public void update(Sp sp) {
        spRepository.save(sp);
    }

    public void delete(Long Id) {
        spRepository.deleteById(Id);
    }

    public void deleteAll() {
        spRepository.deleteAll();
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


    public List<Sp> montarListaSp(List<String> spList) {
        List<Sp> response = new ArrayList<>();

        Sp sp = new Sp();

        sp.setId(Long.valueOf(spList.get(0).substring(6)));

        sp.setNome(spList.get(1).trim().substring(5));

        sp.setTipoObjeto(TipoObjetoEnum.getTipoObjetoEnumByDescricao(spList.get(2).trim().substring(11)));

        String data = spList.get(3).trim().substring(8);
        if (!data.equalsIgnoreCase("null")) {
            sp.setDataDes(LocalDateTime.parse(data));
        }

        data = spList.get(4).trim().substring(8);
        if (!data.equalsIgnoreCase("null")) {
            sp.setDataTqs(LocalDateTime.parse(data));
        }

        data = spList.get(5).trim().substring(8);
        if (!data.equalsIgnoreCase("null")) {
            sp.setDataHmp(LocalDateTime.parse(data));
        }

        data = spList.get(6).trim().substring(8);
        if (!data.equalsIgnoreCase("null")) {
            sp.setDataPrd(LocalDateTime.parse(data));
        }

        sp.setObservacao(spList.get(7).trim().substring(11));

        response.add(sp);

        return response;
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
}