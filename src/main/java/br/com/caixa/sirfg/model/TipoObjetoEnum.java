package br.com.caixa.sirfg.model;

public enum TipoObjetoEnum {
    SP_CARGA(1L, "SP de Carga"),
    SP(2L, "SP"),
    COBOL(3L, "COBOL"),
    JCL(4L, "JCL");

    private final Long valorObjeto;
    private final String descricaoObjeto;

    TipoObjetoEnum(Long valorObjeto, String descricaoObjeto) {
        this.valorObjeto = valorObjeto;
        this.descricaoObjeto = descricaoObjeto;
    }

    public static TipoObjetoEnum getTipoObjetoEnumByDescricao(String descricaoObjeto) {
        for (TipoObjetoEnum tipoObjetoEnum : TipoObjetoEnum.values()) {
            if (tipoObjetoEnum.descricaoObjeto.equalsIgnoreCase(descricaoObjeto)) {
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
