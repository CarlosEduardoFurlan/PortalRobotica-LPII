package br.edu.ifms.robotica.model;

public class Participacao {
	private Long estudanteId;
	private Long atividadeId;
	private String funcao;
	private String descricaoContribuicao;
	private String nomeEstudante;
	private String tituloAtividade;
	
	// Construtor vazio
	public Participacao() {
	}

	// Construtor com todos os atributos
	public Participacao(Long estudanteId, Long atividadeId, String funcao,
	                    String descricaoContribuicao) {

	    this.estudanteId = estudanteId;
	    this.atividadeId = atividadeId;
	    this.funcao = funcao;
	    this.descricaoContribuicao = descricaoContribuicao;
	}

	public Long getEstudanteId() {
		return estudanteId;
	}

	public void setEstudanteId(Long estudanteId) {
		this.estudanteId = estudanteId;
	}

	public Long getAtividadeId() {
		return atividadeId;
	}

	public void setAtividadeId(Long atividadeId) {
		this.atividadeId = atividadeId;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getDescricaoContribuicao() {
		return descricaoContribuicao;
	}

	public void setDescricaoContribuicao(String descricaoContribuicao) {
		this.descricaoContribuicao = descricaoContribuicao;
	}
	
	public String getNomeEstudante() {
	    return nomeEstudante;
	}

	public void setNomeEstudante(String nomeEstudante) {
	    this.nomeEstudante = nomeEstudante;
	}

	public String getTituloAtividade() {
	    return tituloAtividade;
	}

	public void setTituloAtividade(String tituloAtividade) {
	    this.tituloAtividade = tituloAtividade;
	}
	
}
