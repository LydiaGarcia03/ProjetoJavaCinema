package pkg;

import java.io.Serializable;

public class Tarifa implements Serializable {

	private static final long serialVersionUID = 1L;

	private float valorTarifa = 16f;

	public float getValorTarifa() {
		return valorTarifa;
	}
	public void setValorTarifa(float valorTarifa) {
		this.valorTarifa = valorTarifa;
	}

}
