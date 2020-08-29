package br.com.caixa.sirfg.service;

import br.com.caixa.sirfg.model.Ambiente;
import br.com.caixa.sirfg.repository.AmbienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmbienteService {
    private final AmbienteRepository ambienteRepository;

    public AmbienteService(AmbienteRepository ambienteRepository) {
        this.ambienteRepository = ambienteRepository;
    }

    public List<Ambiente> findAll() {
        return ambienteRepository.findAll();
    }

    public Optional<Ambiente> findById(Long id) {
        return ambienteRepository.findById(id);
    }

    public void create(Ambiente ambiente) {
        ambienteRepository.save(ambiente);
    }

    public void update(Ambiente ambiente) {
        ambienteRepository.save(ambiente);
    }
}