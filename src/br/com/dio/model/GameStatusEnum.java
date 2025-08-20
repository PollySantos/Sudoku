package br.com.dio.model;

public enum GameStatusEnum {
    NAO_INICIADO("Não Iniciado"),
    INCOMPLETO_SEM_ERRO("Incompleto (sem erros)"),
    INCOMPLETO_COM_ERRO("Incompleto (com erros)"),
    COMPLETO_SEM_ERRO("Completo (sem erros)"),
    COMPLETO_COM_ERRO("Completo (com erros)");

    private final String label;

    GameStatusEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}