package pkg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Sessao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Date dataInicial, horaInicial, horaFinal;
	private Filme filme;
	private float lucro = 0;
	private String estado;
	private char[][] poltronas = new char[15][10];
	ArrayList<Float> listaPrecoIngresso = new ArrayList<>();
	
	//Constructor Method
	public Sessao() {
		for(int linha = 0; linha < 15; linha++) {
			for (int coluna = 0; coluna < 10; coluna++) {
				poltronas[linha][coluna] = 'L';
			}
		}
		setEstado("Disponível");
	}
	
	// Methods
	public void calcularLucroSessao(Ingresso ingresso) {
		
		listaPrecoIngresso.add(ingresso.getPrecoFinal());
		
		float auxPreco = 0;
		
		for(int i = 0; i < listaPrecoIngresso.size(); i++) {
			
			float precoIngresso = listaPrecoIngresso.get(i);
			
			auxPreco += precoIngresso;
		}
		
		setLucro(auxPreco);
		
	}
	
	public boolean venderIngressos(String linha, int coluna) {
		
		int numLinha = 0;
		
		// Convert words to numbers
		if(linha.equalsIgnoreCase("A")) {
			numLinha = 0;
		}	
		
		if(linha.equalsIgnoreCase("B")) {
			numLinha = 1;
		}
				
		if(linha.equalsIgnoreCase("C")) {
			numLinha = 2;
		}		
		
		if(linha.equalsIgnoreCase("D")) {
			numLinha = 3;
		}
		
		if(linha.equalsIgnoreCase("E")) {
			numLinha = 4;
		}
		
		if(linha.equalsIgnoreCase("F")) {
			numLinha = 5;
		}		
		
		if(linha.equalsIgnoreCase("G")) {
			numLinha = 6;
		}
		
		if(linha.equalsIgnoreCase("H")) {
			numLinha = 7;
		}
		
		if(linha.equalsIgnoreCase("I")) {
			numLinha = 8;
		}
		
		if(linha.equalsIgnoreCase("J")) {
			numLinha = 9;
		}
		
		if(linha.equalsIgnoreCase("K")) {
			numLinha = 10;
		}
		
		if(linha.equalsIgnoreCase("L")) {
			numLinha = 11;
		}
		
		if(linha.equalsIgnoreCase("M")) {
			numLinha = 12;
		}
		
		if(linha.equalsIgnoreCase("N")) {
			numLinha = 13;
		}
		
		if(linha.equalsIgnoreCase("O")) {
			numLinha = 14;
		}
		
		// Verification
		if(poltronas[numLinha][(coluna - 1)] == 'O') {
			return false;
		}
		else {
			poltronas[numLinha][(coluna - 1)] = 'O';
			return true; 
		}
		
	}
	
	// Getters and Setters
	public Date getDataInicial() {
		return dataInicial;
	}

	@SuppressWarnings("deprecation")
	public boolean setDataInicial(Date dataInicial) {
		
		if(dataInicial.getYear() < 18) {
			
			return false;
			
		}
		else {
			
			this.dataInicial = dataInicial;
			
			return true;
			
		}
	}
	
	public Date getHoraInicial() {
		return horaInicial;
	}
	
	public void setHoraInicial(Date horaInicial) {
		this.horaInicial = horaInicial;
	}
	
	public Date getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
	}
	
	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public char getPoltronaPosicao(int linha, int coluna){
		return poltronas[linha][coluna];
	}
	
	public float getLucro() {
		return lucro;
	}
	
	public void setLucro(float lucro) {
		this.lucro = lucro;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	}
