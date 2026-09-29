package br.edu.ifms.robotica.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Atividade {
	private Long id;
	private String titulo;
	private String tipo;
	private String descricao;
	private Date dataInicio;
	private Date dataFim;
	private String status;
	private Long coordenadorId;
	private List<PeriodoLetivo> periodos = new ArrayList<>();
	
	// Construtor vazio
	public Atividade() {
	}

	// Construtor com todos os atributos
	public Atividade(Long id, String titulo, String tipo, String descricao,
	                 Date dataInicio, Date dataFim, String status, Long coordenadorId) {

	    this.id = id;
	    this.titulo = titulo;
	    this.tipo = tipo;
	    this.descricao = descricao;
	    this.dataInicio = dataInicio;
	    this.dataFim = dataFim;
	    this.status = status;
	    this.coordenadorId = coordenadorId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCoordenadorId() {
		return coordenadorId;
	}

	public void setCoordenadorId(Long coordenadorId) {
		this.coordenadorId = coordenadorId;
	}
	
	public List<PeriodoLetivo> getPeriodos() {
	    return periodos;
	}

	public void setPeriodos(List<PeriodoLetivo> periodos) {
	    this.periodos = periodos;
	}
	
}
