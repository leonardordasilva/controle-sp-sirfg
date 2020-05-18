package br.com.caixa.sirfg.model.enumerator;

public enum AmbienteEnum {
    DES(1L, "DES"),
    TQS(2L, "TQS"),
    HMP(3L, "HMP"),
    PRD(4L, "PRD");

    private final Long valorAmbiente;
    private final String nomeAmbiente;

    AmbienteEnum(Long valorAmbiente, String nomeAmbiente) {
        this.valorAmbiente = valorAmbiente;
        this.nomeAmbiente = nomeAmbiente;
    }

    public Long getValorAmbiente() {
        return valorAmbiente;
    }

    public String getNomeAmbiente() {
        return nomeAmbiente;
    }
}