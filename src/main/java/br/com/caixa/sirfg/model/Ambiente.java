package br.com.caixa.sirfg.model;

import br.com.caixa.sirfg.model.enumerator.AmbienteEnum;
import br.com.caixa.sirfg.util.DataFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Ambiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
                return DataFormatter.getDataAmbienteFormatada(dataDes);
            case TQS:
                return DataFormatter.getDataAmbienteFormatada(dataTqs);
            case HMP:
                return DataFormatter.getDataAmbienteFormatada(dataHmp);
            case PRD:
                return DataFormatter.getDataAmbienteFormatada(dataPrd);
            default:
                return null;
        }
    }

    public String getDataDesFormatada() {
        return DataFormatter.getDataFormatada(dataDes);
    }

    public String getDataTqsFormatada() {
        return DataFormatter.getDataFormatada(dataTqs);
    }

    public String getDataHmpFormatada() {
        return DataFormatter.getDataFormatada(dataHmp);
    }

    public String getDataPrdFormatada() {
        return DataFormatter.getDataFormatada(dataPrd);
    }
}
