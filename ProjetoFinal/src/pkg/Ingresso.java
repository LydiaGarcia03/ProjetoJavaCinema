package pkg;

import java.io.Serializable;

public class Ingresso implements Serializable{

	private static final long serialVersionUID = 1L;

	private String tipoIngresso;
	private float precoFinal;
	
	public String getTipoIngresso() {
		return tipoIngresso;
	}
	public void setTipoIngresso(String tipoIngresso) {
		this.tipoIngresso = tipoIngresso;
	}
	public float getPrecoFinal() {
		return precoFinal;
	}
	public void setPrecoFinal(float preco) {
		this.precoFinal = preco;
	}
}
