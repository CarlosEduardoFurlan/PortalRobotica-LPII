package br.edu.ifms.robotica.model;

public class Coordenador {
	private Long id;
	private String nome;
	private String minibio;
	private String foto;
	private String email;
	private String senhaHash;
	private boolean ativo;
	
	
	public Coordenador() {
		
	}
	
	public Coordenador(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMinibio() { return minibio; }
	public void setMinibio(String minibio) { this.minibio = minibio; }
	public String getFoto() { return foto; }
	public void setFoto(String foto) { this.foto = foto; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getSenhaHash() { return senhaHash; }
	public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }
	public boolean isAtivo() { return ativo; }
	public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
