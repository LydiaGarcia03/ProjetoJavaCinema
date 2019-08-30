package pkg;
import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable{

	private static final long serialVersionUID = 1L;
	
	ArrayList<Sessao> listaDeSessoes = new ArrayList<>();
	ArrayList<Filme> listaDeFilmes = new ArrayList<>();
	Tarifa tarifa = new Tarifa();
	
	// Methods
	public void adicionarSessao(Sessao sessao) {
		
		listaDeSessoes.add(sessao);
	}
	
	public Sessao buscarSessao(int op) {
		
		Sessao sessao = null;
		
		for(int i = 0; i < listaDeSessoes.size(); i++) {
			
			sessao = listaDeSessoes.get(i);
			
			if(op == (i + 1)) {
				
				return sessao;
				
			}
		}
		return null;
	}
	
	public void removerSessao(Sessao sessao) {
		
		listaDeSessoes.remove(sessao);
	}
	
	public void adicionarFilme(Filme filme) {
		
		listaDeFilmes.add(filme);
		
	}
	
	public Filme buscarFilmes(int op) {
		
		Filme filme = null;
		
		for(int i = 0; i < listaDeFilmes.size(); i++) {
			
			filme = listaDeFilmes.get(i);
			
			if(op == (i + 1)) {
				
				return filme;
				
			}	
		}
		
		return null;
		
	}
	
	public void removerFilme(Filme filme) {
		
		listaDeFilmes.remove(filme);
		
	}
	
}