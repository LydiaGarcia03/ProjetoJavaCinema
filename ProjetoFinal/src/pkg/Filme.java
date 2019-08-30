package pkg;

import java.io.Serializable;

public class Filme implements Serializable{

	private static final long serialVersionUID = 1L;

	private String titulo, genero,classificacaoEtaria;

	// Getters and Setters
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getClassificacaoEtaria() {
		return classificacaoEtaria;
	}
	public void setClassificacaoEtaria(String classificacaoEtaria) {
		this.classificacaoEtaria = classificacaoEtaria;
	}
	
}
