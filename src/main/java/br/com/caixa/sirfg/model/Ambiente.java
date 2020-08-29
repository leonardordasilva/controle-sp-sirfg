package br.com.caixa.sirfg.model;

import br.com.caixa.sirfg.model.enumerator.AmbienteEnum;
import br.com.caixa.sirfg.util.DataFormatter;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class Ambiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotNull
    private String versaoDes;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataDes;

    @NotNull
    private String versaoTqs;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataTqs;

    @NotNull
    private String versaoHmp;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHmp;

    @NotNull
    private String versaoPrd;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataPrd;

    public String getDataFormatada(AmbienteEnum ambienteEnum) {
        switch (ambienteEnum) {
            case DES:
                return DataFormatter.getDataFormatada(dataDes);
            case TQS:
                return DataFormatter.getDataFormatada(dataTqs);
            case HMP:
                return DataFormatter.getDataFormatada(dataHmp);
            case PRD:
                return DataFormatter.getDataFormatada(dataPrd);
            default:
                return null;
        }
    }

    public String getDataDesFormatada() {
        return DataFormatter.getDataHoraFormatada(dataDes);
    }

    public String getDataTqsFormatada() {
        return DataFormatter.getDataHoraFormatada(dataTqs);
    }

    public String getDataHmpFormatada() {
        return DataFormatter.getDataHoraFormatada(dataHmp);
    }

    public String getDataPrdFormatada() {
        return DataFormatter.getDataHoraFormatada(dataPrd);
    }
}
