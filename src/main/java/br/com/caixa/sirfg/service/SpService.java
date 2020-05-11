package br.com.caixa.sirfg.service;

import br.com.caixa.sirfg.model.Sp;
import br.com.caixa.sirfg.repository.SpRepository;
import org.springframework.stereotype.Service;

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
}