package br.edu.ifms.robotica.model;

public class AtividadePeriodo {

    private Long atividadeId;
    private Long periodoId;
    private String tituloAtividade;
    private String periodoFormatado;

    public AtividadePeriodo() {
    }

    public AtividadePeriodo(Long atividadeId, Long periodoId) {
        this.atividadeId = atividadeId;
        this.periodoId = periodoId;
    }

    public Long getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(Long atividadeId) {
        this.atividadeId = atividadeId;
    }

    public Long getPeriodoId() {
        return periodoId;
    }

    public void setPeriodoId(Long periodoId) {
        this.periodoId = periodoId;
    }
    
    public String getTituloAtividade() {
        return tituloAtividade;
    }

    public void setTituloAtividade(String tituloAtividade) {
        this.tituloAtividade = tituloAtividade;
    }

    public String getPeriodoFormatado() {
        return periodoFormatado;
    }

    public void setPeriodoFormatado(String periodoFormatado) {
        this.periodoFormatado = periodoFormatado;
    }
}