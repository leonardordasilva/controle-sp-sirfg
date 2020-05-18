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
import java.time.LocalDate;

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

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDes;

    @NotNull
    private String versaoTqs;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataTqs;

    @NotNull
    private String versaoHmp;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataHmp;

    @NotNull
    private String versaoPrd;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPrd;

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

    public String getDataFormatada(LocalDate data) {
        return DataFormatter.getDataAmbienteFormatada(data);
    }
}
