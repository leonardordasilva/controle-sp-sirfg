package br.com.caixa.sirfg.model;

public enum AmbienteEnum {
    DES(1, "DES"),
    TQS(2, "TQS"),
    HMP(3, "HMP"),
    PRD(4, "PRD");

    private final int valorAmbiente;
    private final String nomeAmbiente;

    AmbienteEnum(int valorAmbiente, String nomeAmbiente) {
        this.valorAmbiente = valorAmbiente;
        this.nomeAmbiente = nomeAmbiente;
    }

    public int getValorAmbiente() {
        return valorAmbiente;
    }

    public String getNomeAmbiente() {
        return nomeAmbiente;
    }
}