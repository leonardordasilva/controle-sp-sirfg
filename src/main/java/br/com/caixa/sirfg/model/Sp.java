package br.com.caixa.sirfg.model;

import br.com.caixa.sirfg.model.enumerator.AmbienteEnum;
import br.com.caixa.sirfg.model.enumerator.TipoObjetoEnum;
import br.com.caixa.sirfg.util.DataFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Sp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String observacao;

    @Transient
    private List<String> SpList = new ArrayList<>();

    public String getDataFormatada(AmbienteEnum ambienteEnum) {
        switch (ambienteEnum) {
            case DES:
                return getDataFormatada(dataDes);
            case TQS:
                return getDataFormatada(dataTqs);
            case HMP:
                return getDataFormatada(dataHmp);
            case PRD:
                return getDataFormatada(dataPrd);
            default:
                return null;
        }
    }

    public String getDataDesFormatada() {
        return getDataFormatada(AmbienteEnum.DES);
    }

    public String getDataTqsFormatada() {
        return getDataFormatada(AmbienteEnum.TQS);
    }

    public String getDataHmpFormatada() {
        return getDataFormatada(AmbienteEnum.HMP);
    }

    public String getDataPrdFormatada() {
        return getDataFormatada(AmbienteEnum.PRD);
    }

    public String getDataFormatada(LocalDateTime data) {
        return DataFormatter.getDataFormatada(data);
    }
}