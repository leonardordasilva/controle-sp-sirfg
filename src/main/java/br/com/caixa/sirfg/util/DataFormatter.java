package br.com.caixa.sirfg.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataFormatter {
    public static String getDataHoraFormatada(LocalDateTime data) {
        if (data != null) {
            return data.format(getDateTimeFormatter());
        }

        return "";
    }

    public static String getDataFormatada(LocalDateTime data) {
        if (data != null) {
            return data.format(getDateFormatter());
        }

        return "";
    }

    private static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    }

    protected static DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }
}
