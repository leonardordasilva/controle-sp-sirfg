package br.com.caixa.sirfg.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class InformacaoSp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataManipulacao;

    @NotNull
    private String usuario;

    @NotNull
    private String motivo;

    private Long objetoId;

    @PrePersist
    @PreUpdate
    public void prepareStrings() {
        usuario = usuario.toUpperCase();
        motivo = motivo.toUpperCase();
    }
}
