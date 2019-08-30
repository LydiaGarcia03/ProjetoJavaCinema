package pkg;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu implements Serializable{
	
	private static final long serialVersionUID = 1L;

	Scanner teclado = new Scanner(System.in);
	SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yy");
	SimpleDateFormat horaFormat = new SimpleDateFormat("hh:mm");
	Persistencia persistencia = new Persistencia();
	
	Cinema cinema = persistencia.recuperar();
	
	public void iniciar() {
		
		int op;

		System.out.println("\t\tSISTEMA DE CINEMA");		
				
		do {
			
			mostrarMenu();
			
			System.out.print("\nSelecione a ação que desejas realizar: ");
			op = teclado.nextInt();
			
			teclado.nextLine();
			
			switch(op) {
		
				case 1:
					Filme filme = new Filme();
										
					System.out.print("\n>> Insira o título do filme: ");
					filme.setTitulo(teclado.nextLine());
					
					System.out.print(">> Insira o gênero do filme: ");
					filme.setGenero(teclado.nextLine());
					
					System.out.print(">> Insira a classificação etária: ");
					filme.setClassificacaoEtaria(teclado.nextLine());
					
					cinema.adicionarFilme(filme);
					
					System.out.println("\nFilme cadastrado com sucesso!");
					break;
					
				case 2:
					int opFilmeExcluir;
					
					System.out.println("\n----------------- Lista de Filmes ----------------");
					listarFilmes();

					System.out.print("\n>> Selecione o filme a excluir: ");
					opFilmeExcluir = teclado.nextInt();
					
					filme = cinema.buscarFilmes(opFilmeExcluir);
					
					cinema.removerFilme(filme);
					
					System.out.println("\nFilme removido com sucesso!");
					break;
					
				case 3:
					Sessao sessao = new Sessao();
					
					int opFilmeSessao;
					
					System.out.println("\n----------------- Lista de Filmes ----------------");
					listarFilmes();
					
					System.out.print("\nSelecione o filme para inserir na sessão: ");
					opFilmeSessao = teclado.nextInt();
					
					sessao.setFilme(cinema.listaDeFilmes.get(opFilmeSessao - 1));
					
					teclado.nextLine();
					
					System.out.print("\n>> Insira a data de início da sessão: ");
					String dataInicial = teclado.nextLine();
					
					System.out.print(">> Insira o horário de início da sessão: ");
					String horaInicial = teclado.nextLine();

					Date dtInicial = null;
					Date hInicial = null;
					
					try {
						
						dtInicial = dataFormat.parse(dataInicial);
						hInicial = horaFormat.parse(horaInicial);
						
					} 
					catch (ParseException e) {
						e.printStackTrace();
					}
					
					if(sessao.setDataInicial(dtInicial) == false) {

						System.out.println("\n*** Data antiquada ***");
						
					}
					else {
						
						sessao.setDataInicial(dtInicial);
						sessao.setHoraInicial(hInicial);
						
						cinema.adicionarSessao(sessao);
						
						System.out.println("\nSessão cadastrada com sucesso!");
					}
					
					break;
					
				case 4:
					int opSessaoExcluir;
					
					System.out.println("\n---------------- Lista de Sessões ----------------");
					listarSessao();
					
					System.out.print("\n>> Selecione a sessão a excluir: ");
					opSessaoExcluir = teclado.nextInt();
					
					sessao = cinema.buscarSessao(opSessaoExcluir);
					
					cinema.removerSessao(sessao);
					
					System.out.println("\nSessão removida com sucesso!");
					break;
					
				case 5:
					sessao = null;
					int opSessaoTerminada;
					
					System.out.println("\n---------------- Lista de Sessões ----------------");
					listarSessao();
					
					System.out.print("\n>> Selecione uma sessão: ");
					opSessaoTerminada = teclado.nextInt();
					
					teclado.nextLine();
					
					sessao = cinema.listaDeSessoes.get(opSessaoTerminada - 1);
					
					if(sessao.getEstado().equals("Indisponível")) {
						
						System.out.println("\n*** Sessão já foi encerrada ***");
						
					}
					else {
						
						System.out.print("\n>> Insira o horário de término da sessão: ");
						String horaFinal = teclado.nextLine();
									        
						Date hFinal = null;
						
						try {
							hFinal = horaFormat.parse(horaFinal);
						} 
						catch (ParseException e) {
							e.printStackTrace();
						}
				       
						sessao.setHoraFinal(hFinal);
						
						sessao.setEstado("Indisponível");
						
						System.out.println("\nSessão terminada!");
						
					}
					
					break;
					
				case 6:
					System.out.println("\nValor do ingresso: R$ " + cinema.tarifa.getValorTarifa());
					
					System.out.print("\n>> Insira um novo valor para o ingresso: R$ ");
					cinema.tarifa.setValorTarifa(teclado.nextFloat());
					
					System.out.println("\nValor do ingresso atual: R$ " + cinema.tarifa.getValorTarifa());
					break;
					
				case 7:
					Ingresso ingresso = new Ingresso();
					
					int opTipoIngresso;
					
					System.out.println("\n--------------- Tipos de Ingresso ----------------\n");
					System.out.println("(1) - Inteira");
					System.out.println("(2) - Meia Entrada");
					
					System.out.print("\n>> Selecione o ingresso a comprar: ");
					opTipoIngresso = teclado.nextInt();
										
					if(opTipoIngresso == 1) {
						ingresso.setPrecoFinal(cinema.tarifa.getValorTarifa());
					}
					else if(opTipoIngresso == 2) {
						ingresso.setPrecoFinal((cinema.tarifa.getValorTarifa()) / 2);
					}
										
					sessao = null;
					int opSessao;
					
					System.out.println("\n---------------- Lista de Sessões ----------------");
					listarSessao();
					
					System.out.print("\n>> Selecione uma sessão: ");
					opSessao = teclado.nextInt();
										
					sessao = cinema.listaDeSessoes.get(opSessao - 1);
					
					if(sessao.getEstado().equals("Indisponível")) {
						
						System.out.println("\n*** Sessão já está terminada ***");
						
					} 
					else {

						System.out.println("\n--------------- Lista de Poltronas ---------------\n");
						
						System.out.println("|-----------------------------------------------|"); 
						System.out.println("|-----------------------------------------------|"); 
						System.out.println("|---------------------Tela----------------------|");
						System.out.println("|_______________________________________________|");
						System.out.println("| | 1 – 2 – 3 – 4 – 5 – 6 – 7 – 8 – 9 – 10|-----|");
						System.out.println("|--------------------------------------------E--|");
						
						// Line 01
						System.out.print("|A| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(0, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(0, 9));
						System.out.println(" |--N--|");
						
						// Line 02
						System.out.print("|B| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(1, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(1, 9));
						System.out.println(" |--T--|");
						
						// Line 03
						System.out.print("|C| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(2, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(2, 9));
						System.out.println(" |--R--|");
						
						// Line 04
						System.out.print("|D| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(3, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(3, 9));
						System.out.println(" |--A--|");
						
						// Line 05
						System.out.print("|E| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(4, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(4, 9));
						System.out.println(" |--D--|");
						
						// Line 06
						System.out.print("|F| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(5, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(5, 9));
						System.out.println(" |--A--|");
						
						// Line 07
						System.out.print("|G| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(6, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(6, 9));
						System.out.println(" |-----|");
						
						// Line 08
						System.out.print("|H| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(7, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(7, 9));
						System.out.println(" |--C--|");
						
						// Line 09
						System.out.print("|I| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(8, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(8, 9));
						System.out.println(" |--O--|");
						
						// Line 10
						System.out.print("|J| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(9, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(9, 9));
						System.out.println(" |--R--|");
						
						// Line 11
						System.out.print("|K| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(10, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(10, 9));
						System.out.println(" |--R--|");
						
						// Line 12
						System.out.print("|L| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(11, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(11, 9));
						System.out.println(" |--E--|");
						
						// Line 13
						System.out.print("|M| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(12, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(12, 9));
						System.out.println(" |--D--|");
						
						// Line 14
						System.out.print("|N| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(13, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(13, 9));
						System.out.println(" |--O--|");
						
						// Line 15
						System.out.print("|O| ");
						for (int coluna = 0; coluna < 9; coluna++) {
							System.out.print(sessao.getPoltronaPosicao(14, coluna));
							System.out.print(" - ");
						}
						System.out.print(sessao.getPoltronaPosicao(14, 9));
						System.out.println(" |--R--|");
						
						System.out.println("\nL - Livre");
						System.out.println("O - Ocupada");
						System.out.println("Fileira A - Especial");
						
						teclado.nextLine();
						
						String linha;
						int coluna;
						
						System.out.println("\n\nQual poltrona desejas comprar?\n");
						System.out.print(">> Linha: ");
						linha = teclado.nextLine();
						System.out.print(">> Coluna: ");
						coluna = teclado.nextInt();
						
						boolean estadoPoltrona = sessao.venderIngressos(linha, coluna);
						
						if(estadoPoltrona == true) {
							
							sessao.calcularLucroSessao(ingresso);
							
							System.out.println("\nIngresso vendido com sucesso!");
							
						}
						else {
							System.out.println("\n*** Poltrona já está ocupada ***");
						}
					}
					
					break;
					
				case 8:
					int opRelatorio;
					
					System.out.println("\n---------------- Lista de Sessões ----------------");
					listarSessao();
					
					System.out.print("\n>> Selecione a sessão desejada: ");
					opRelatorio = teclado.nextInt();
					
					sessao = cinema.buscarSessao(opRelatorio);
					
					System.out.println("\n----------------- Relatório -------------------\n");
					System.out.println("Data: " + dataFormat.format(sessao.getDataInicial()));
					System.out.println("Horário de início: " + horaFormat.format(sessao.getHoraInicial()) + "h");
					System.out.println("\nFilme transmitido: " + sessao.getFilme().getTitulo());
					System.out.println("Gênero do filme: " + sessao.getFilme().getGenero());
					System.out.println("Classificação etária: " + sessao.getFilme().getClassificacaoEtaria());
					System.out.println("\nEstado da sessão: " + sessao.getEstado());
					System.out.println("\nLucro gerado: R$ " + sessao.getLucro());
					
					break;
					
				case 0:
					persistencia.salvar(cinema);
					System.out.println("\n\nObrigada por utilizar este sistema!");
					break;
					
				default:
					System.out.println("\n*** Ação não permitida! ***");
					break;
			}
			
		} while(op != 0);
		
		teclado.close();
		
	}
	
	public void mostrarMenu() {
		
		System.out.println("\n\n -------------------- Menu --------------------- ");
		System.out.println("|\t\t\t\t\t\t|");
		System.out.println("|   (1) - Cadastrar filme\t\t\t|");
		System.out.println("|   (2) - Remover filme\t\t\t\t|");
		System.out.println("|   (3) - Cadastrar sessão\t\t\t|");
		System.out.println("|   (4) - Remover sessão \t\t\t|");
		System.out.println("|   (5) - Marcar sessão como terminada\t\t|");
		System.out.println("|   (6) - Alterar tarifas\t\t\t|");
		System.out.println("|   (7) - Vender ingressos\t\t\t|");
		System.out.println("|   (8) - Visualizar um relatório de lucros\t|");
		System.out.println("|   (0) - Sair\t\t\t\t\t|");
		System.out.println("|\t\t\t\t\t\t|");
		System.out.println("|   Entrada inteira: R$ " + cinema.tarifa.getValorTarifa() + "\t\t\t|");
		System.out.println("|   Meia Entrada: R$ " + (cinema.tarifa.getValorTarifa()) / 2 + "\t\t\t|");
		System.out.println("|\t\t\t\t\t\t|");
		System.out.println(" ----------------------------------------------- ");
		
	}
	
	public void listarFilmes() {
		
		for(int i = 0; i < cinema.listaDeFilmes.size(); i++) {
			
			Filme filme = cinema.listaDeFilmes.get(i);
			
			System.out.println("\n(" + (i + 1) + ")");
			System.out.println("Título: " + filme.getTitulo());
			System.out.println("Gênero: " + filme.getGenero());
			System.out.println("Classificação etária: " + filme.getClassificacaoEtaria());
			
		}
	}
	
	public void listarSessao() {
		
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yy");
		SimpleDateFormat horaFormat = new SimpleDateFormat("hh:mm");
		
		for(int i = 0; i < cinema.listaDeSessoes.size(); i++) {
			
			Sessao sessao = cinema.listaDeSessoes.get(i);
			
			System.out.println("\n(" + (i + 1) + ")");			
			
			System.out.println("Data: " + dataFormat.format(sessao.getDataInicial()));
			System.out.println("Horário de início: " +  horaFormat.format(sessao.getHoraInicial()) + "h");
			System.out.println("Filme: " + sessao.getFilme().getTitulo());
			System.out.println("Estado: " + sessao.getEstado());
			
		}
	}
	
}