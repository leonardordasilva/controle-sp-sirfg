package br.com.caixa.sirfg.model;

public enum TipoObjetoEnum {
    SP_CARGA(1L, "SP_CARGA", "SP de Carga"),
    SP(2L, "SP", "SP"),
    COBOL(3L, "COBOL", "COBOL"),
    JCL(4L, "JCL", "JCL"),
    BIND(5L, "BIND", "BIND");

    private final Long valorObjeto;
    private final String codigoObjeto;
    private final String descricaoObjeto;

    TipoObjetoEnum(Long valorObjeto, String codigoObjeto, String descricaoObjeto) {
        this.valorObjeto = valorObjeto;
        this.codigoObjeto = codigoObjeto;
        this.descricaoObjeto = descricaoObjeto;
    }

    public static TipoObjetoEnum getTipoObjetoEnumByDescricao(String codigoObjeto) {
        for (TipoObjetoEnum tipoObjetoEnum : TipoObjetoEnum.values()) {
            if (tipoObjetoEnum.codigoObjeto.equalsIgnoreCase(codigoObjeto)) {
                return tipoObjetoEnum;
            }
        }
        return null;
    }

    public Long getValorObjeto() {
        return valorObjeto;
    }

    public String getDescricaoObjeto() {
        return descricaoObjeto;
    }
}
