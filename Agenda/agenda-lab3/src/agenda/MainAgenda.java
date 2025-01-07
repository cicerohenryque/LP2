package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 *
 */
public class MainAgenda {

	public static void main(String[] args) {
		Agenda agenda = new Agenda();

		System.out.println("Carregando agenda inicial");
		try {
			/*
			 * Essa é a maneira de lidar com possíveis erros por falta do arquivo. 
			 */
			carregaAgenda("agenda_inicial.csv", agenda);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, agenda, scanner);
		}

	}

	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário.
	 * @return O comando escolhido.
	 */
	private static String menu(Scanner scanner) {
		System.out.println(
						"\n" + 
						"(C)adastrar Contato\n" + 
						"(L)istar Contatos\n" + 
						"(E)xibir Contato\n" +
						"(F)avoritos\n"+
						"(A)dicionar Favorito\n"+
						"(R)emover Favorito\n"+
						"(S)air\n" +
						"\n" +
						"Opção> ");
		return scanner.next().toUpperCase();
	}

	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, Agenda agenda, Scanner scanner) {
		switch (opcao) {
		case "C":
			cadastraContato(agenda, scanner);
			break;
		case "L":
			listaDeContatos(agenda);
			break;
		case "E":
			getContato(agenda, scanner);
			break;
		case "A":
			cadastrarFavorito(agenda, scanner);
			break;
		case "F":
			listaDeFavoritos(agenda);
			break;
		case "R":
			removerFavorito(agenda, scanner);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.print("Opção inválida!");
		}
	}

	/**
	 * Imprime lista de contatos da agenda.
	 * 
	 * @param agenda A agenda sendo manipulada.
	 */
	private static void listaDeContatos(Agenda agenda) {
		System.out.print("\nLista de contatos: ");
		System.out.println(agenda.listaDeContatos());
	}

	/**
	 * Imprime os detalhes de um dos contatos da agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void getContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nQual contato> ");
		int posicao = scanner.nextInt();
		if(!(agenda.verificaExistencia(posicao))) {
			System.out.println("POSICAO INVALIDA");
			
		} else {
			System.out.println(agenda.getContato(posicao));
		}
	}

	/**
	 * Cadastra um contato na agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	private static void cadastraContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nPosição na agenda> ");
		int posicao = scanner.nextInt();
		System.out.print("\nNome> ");
		String nome = scanner.next();
		System.out.print("\nSobrenome> ");
		String sobrenome = scanner.next();
		System.out.print("\nTelefone> ");
		String telefone = scanner.next();
		telefone += scanner.nextLine();
		agenda.cadastraContato(posicao, nome, sobrenome, telefone);
		System.out.println("CADASTRO REALIZADO");
	}

	/**
	 * Cadastra um favorito na agenda. 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	public static void cadastrarFavorito(Agenda agenda, Scanner scanner) {
		System.out.print("Contato> ");
		int contato = scanner.nextInt();
		System.out.print("Posicao> ");
		int posicaoDoFavorito = scanner.nextInt();
		try {
			agenda.cadastrarFavorito(contato, posicaoDoFavorito);
			System.out.println("CONTATO FAVORITADO NA POSICAO " + posicaoDoFavorito + "!");
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Remove um contato favorito da agenda.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	public static void removerFavorito(Agenda agenda, Scanner scanner) {
		System.out.println("Posicao> ");
		int posicaoDoFavorito = scanner.nextInt();
		agenda.removerFavorito(posicaoDoFavorito);
	}

	/**
	 * Lista os contatos favoritos da agenda.
	 * @param agenda  A agenda que estamos manipulando.
	 **/
	public static void listaDeFavoritos(Agenda agenda) {
		System.out.print(agenda.listaDeFavoritos());
	}

	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

	/**
	 * Lê uma agenda de um arquivo csv. 
	 * 
	 * @param arquivoContatos O caminho para o arquivo.
	 * @param agenda A agenda que deve ser populada com os dados. 
	 * @throws IOException Caso o arquivo não exista ou não possa ser lido.
	 */
	private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		LeitorDeAgenda leitor = new LeitorDeAgenda();
		
		int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
		System.out.println("Carregamos " + carregados + " registros.");
	}
}
