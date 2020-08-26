package br.com.caixa.sirfg.model;

import br.com.caixa.sirfg.model.enumerator.AmbienteEnum;
import br.com.caixa.sirfg.model.enumerator.TipoObjetoEnum;
import br.com.caixa.sirfg.util.DataFormatter;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Sp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotNull
    private String nome;

    @Enumerated
    @NotNull
    private TipoObjetoEnum tipoObjeto;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataDes;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataTqs;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHmp;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataPrd;

    @JoinColumn(name = "objetoId")
    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("dataManipulacao DESC")
    private List<InformacaoSp> informacoes = new ArrayList<>();

    @Transient
    private List<String> SpList = new ArrayList<>();

    @PrePersist
    @PreUpdate
    public void prepareStrings() {
        nome = nome.toUpperCase();
    }

    public String getDataFormatada(LocalDateTime data) {
        return DataFormatter.getDataHoraFormatada(data);
    }
}