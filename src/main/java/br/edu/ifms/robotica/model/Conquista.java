package br.edu.ifms.robotica.model;

import java.sql.Date;

public class Conquista {

    private Long id;
    private Long atividadeId;
    private String titulo;
    private Date data;
    private String descricao;

    // Construtor vazio
    public Conquista() {
    }

    // Construtor com todos os atributos
    public Conquista(Long id, Long atividadeId, String titulo,
                     Date data, String descricao) {
        this.id = id;
        this.atividadeId = atividadeId;
        this.titulo = titulo;
        this.data = data;
        this.descricao = descricao;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(Long atividadeId) {
        this.atividadeId = atividadeId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}