package br.com.caixa.sirfg.model;

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
import java.time.format.DateTimeFormatter;

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

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataDes;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataTqs;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHmp;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataPrd;

	public String getDataDesFormatado() {
		if (dataDes != null) {
			return dataDes.format(getDateTimeFormatter());
		}

		return "";
	}

	public String getDataTqsFormatado() {
		if (dataTqs != null) {
			return dataTqs.format(getDateTimeFormatter());
		}

		return "";
	}

	public String getDataHmpFormatado() {
		if (dataHmp != null) {
			return dataHmp.format(getDateTimeFormatter());
		}

		return "";
	}

	public String getDataPrdFormatado() {
		if (dataPrd != null) {
			return dataPrd.format(getDateTimeFormatter());
		}

		return "";
	}

	private DateTimeFormatter getDateTimeFormatter() {
		return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	}
}