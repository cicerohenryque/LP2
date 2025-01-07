package documento;


import java.util.ArrayList;
import java.util.HashMap;

import elemento.Elemento;

/**
 * Classe Controller que gerencia as requisições do sistema para as operações de documentos, 
 * tratando funcionalidades de armazenamento, de requisição de documentos e gerenciando
 * requisições aos métodos de documento
 */

public class DocumentoController {
	private DocumentoRepository repositorio;
	
	/**
	 * Construtor de DocumentoController, que somente inicia o armazenamento de Documento
	 * através do DocumentoRepository
	 */
	
	public DocumentoController() {
		this.repositorio = new DocumentoRepository();
	}
	
	/**
	 * Método que adiciona um Documento no repositorio passando o seu título
	 * @param titulo Titulo do Documento que será criado
	 * @return boolean Boolean indicando se o Documento foi adicionado ou não
	 */
	
	public boolean addDocumento(String titulo) {
		if (composedOfSpaces(titulo) || titulo == "") throw new IllegalArgumentException("Título vazio");
		
		return(repositorio.add(titulo));
	}
	
	/**
	 * Método que adiciona um Documento no repositorio passando o seu título e o tamanho
	 * máximo de elementos que pode atingir
	 * @param titulo Titulo do Documento que será criado
	 * @param tamanhoMaximo Tamanho máximo de Elementos que o Documento pode atingir
	 * @return boolean Boolean indicando se o Documento foi adicionado ou não
	 */
	
	public boolean addDocumento(String titulo, int tamanhoMaximo) {
		if (tamanhoMaximo <= 0) throw new IllegalArgumentException("Tamanho inválido");
		if (composedOfSpaces(titulo) || titulo == "") throw new IllegalArgumentException("Título vazio");

		return(repositorio.add(titulo, tamanhoMaximo));
	}
	
	/**
	 * Método que exclui um Documento do repositorio
	 * @param titulo Titulo do Documento que será excluido
	 */
	
	public void removerDocumento(String titulo) {
		if (composedOfSpaces(titulo) || titulo == "") throw new IllegalArgumentException("Título vazio");
		
		repositorio.remove(titulo);
	}
	
	/**
	 * Método que retorna a quantidade de Elementos de um Documento em específico 
	 * acessado através do parâmetro titulo
	 * @param titulo Titulo do Documento que terá sua quantidade de Elementos
	 * retornada
	 * @return int Quantidade de Elementos do Documento requisitado
	 */
	
	public int getQtdeElementos(String titulo) {
		if (composedOfSpaces(titulo) || titulo == "") throw new IllegalArgumentException("Título vazio");
		
		return repositorio.get(titulo).getQtdeElementos();
	}
	
	/**
	 * Método que retorna um array de Strings com a representação resumida de cada
	 * um dos Elementos do Documento acessado através do parâmetro titulo
	 * @param titulo Titulo do Documento que será exibido
	 * @return String[] Array de Strings das representações resumidas dos Elementos
	 * do Documento
	 */
	
	public String[] exibirDocumento(String titulo) {
		if (composedOfSpaces(titulo) || titulo == "") throw new IllegalArgumentException("Título vazio");
		
		return repositorio.get(titulo).getDocumento();
	}
	
	/**
	 * Método que adiciona um Atalho no Documento requisitado, passando o titulo
	 * do Documento que terá um Atalho adicionado e o titulo do Documento que 
	 * será transformado em atalho, verificando antes se o Documento já possui
	 * Atalho
	 * @param tituloDoc Titulo do Documento que terá um atalho adicionado
	 * @param tituloDocReferenciado Titulo do Documento que será transformado
	 * em Atalho
	 * @return int Posicao do Elemento no armazenamento de Elementos
	 */
	
	public int addAtalho(String tituloDoc, String tituloDocReferenciado) {
		if (composedOfSpaces(tituloDoc) || tituloDoc == "") throw new IllegalArgumentException("Título vazio");
		if (composedOfSpaces(tituloDocReferenciado) || tituloDocReferenciado == "") throw new IllegalArgumentException("Título vazio");
		
		if (repositorio.get(tituloDoc).haveDoc()) throw new IllegalStateException("Documento possui atalho");
		
		Documento docReferenciado = acessaDocumento(tituloDocReferenciado);
		
		int prioridade = getMediaPrioridades(docReferenciado.getElementos());
		String valor = tituloDocReferenciado;
		HashMap<String, String> propriedades = new HashMap<String, String>();
		propriedades.put("representacaoCompleta", criaRepresentacaoCompletaAtalho(docReferenciado.getElementos()));
		propriedades.put("representacaoResumido", criaRepresentacaoResumidaAtalho(docReferenciado.getElementos()));
		
		return acessaDocumento(tituloDoc).addAtalho(prioridade, valor, propriedades);
	}
	
	/**
	 * Método que acessa e retorna um Documento
	 * @param titulo Titulo do Documento que será acessado
	 * @return Documento Documento requisitado
	 */
	
	public Documento acessaDocumento(String titulo) {
		if (composedOfSpaces(titulo) || titulo == "") throw new IllegalArgumentException("Título vazio");
		
		return repositorio.get(titulo);
	}
	
	/**
	 * Método que repassa a requisição de adicionar um Elemento Texto ao Documento
	 * @param idDocumento Id do Documento que terá o Elemento Texto adicionado
	 * @param valor Valor do Elemento Texto adicionado
	 * @param prioridade Prioridade do Elemento Texto adicionado
	 * @return int Posição do Elemento adicionado no armazenamento de Elementos do Documento
	 */
	
	public int addTextoDocumento(String idDocumento, String valor, int prioridade) {
		return acessaDocumento(idDocumento).addTexto(valor, prioridade);
	}
	
	/**
	 * Método que repassa a requisição de adicionar um Elemento Titulo ao Documento
	 * @param idDocumento Id do Documento que terá o Elemento Titulo adicionado
	 * @param valor Valor do Elemento Titulo adicionado
	 * @param prioridade Prioridade do Elemento Titulo adicionado
	 * @param nivel Nivel do Elemento Titulo adicionado
	 * @param boolean Boolean indicando se o Elemento Titulo é linkável ou não
	 * @return int Posição do Elemento adicionado no armazenamento de Elementos do Documento
	 */
	
	public int addTituloDocumento(String idDocumento, String valor, int prioridade, int nivel, boolean linkavel) {
		return acessaDocumento(idDocumento).addTitulo(valor, prioridade, nivel, linkavel);
	}
	
	/**
	 * Método que repassa a requisição de adicionar um Elemento Lista ao Documento
	 * @param idDocumento Id do Documento que terá o Elemento Lista adicionado
	 * @param valorLista Valor do Elemento Lista adicionado
	 * @param prioridade Prioridade do Elemento Lista adicionado
	 * @param separador Caracter que indicará onde será feita a divisão do Valor da Lista
	 * @param charLista Caracter que será utilizado para a exibição do Elemento Lista
	 * @return int Posição do Elemento adicionado no armazenamento de Elementos do Documento
	 */
	
	public int addListaDocumento(String idDocumento, String valorLista, int prioridade, String separador, String charLista) {
		return acessaDocumento(idDocumento).addLista(valorLista, prioridade, separador, charLista);
	}
	
	/**
	 * Método que repassa a requisição de adicionar um Elemento Termos ao Documento
	 * @param idDocumento Id do Documento que terá o Elemento Termos adicionado
	 * @param valorTermos Valor do Elemento Termos adicionado
	 * @param prioridade Prioridade do Elemento Termos adicionado
	 * @param separador Caracter que indicará onde será feita a divisão do Valor da Lista
	 * @param ordem Ordem em que os Termos irão ser representados podendo ser por ordem 
	 * alfabética, de tamanho ou sem ordem
	 * @return int Posição do Elemento adicionado no armazenamento de Elementos do Documento
	 */
	
	public int addTermosDocumento(String idDocumento, String valorTermos, int prioridade, String separador, String ordem) {
		return acessaDocumento(idDocumento).addTermos(valorTermos, prioridade, separador, ordem);
	}
	
	/**
	 * Método que repassa a requisição de retornar uma String de representação Completa de um 
	 * Elemento do Documento para o Documento acessado através dos parâmetros idDocumento para 
	 * acessar o Documento e posicao para acessar o Elemento
	 * @param idDocumento Id do Documento que terá o Elemento exibido
	 * @param posicao Posição do Elemento no armazenamento de Elementos do Documento requisitado
	 * @return String representação completa do Elemento requisitado
	 */
	
	public String getRepresentacaoCompletaDocumento(String idDocumento, int posicao) {
		return acessaDocumento(idDocumento).getRepresentacaoCompleta(posicao);
	}
	
	/**
	 * Método que repassa a requisição de retornar uma String de representação Resumida de um 
	 * Elemento do Documento para o Documento acessado através dos parâmetros idDocumento para 
	 * acessar o Documento e posicao para acessar o Elemento
	 * @param idDocumento Id do Documento que terá o Elemento exibido
	 * @param posicao Posição do Elemento no armazenamento de Elementos do Documento requisitado
	 * @return String representação resumida do Elemento requisitado
	 */
	
	public String getRepresentacaoResumidaDocumento(String idDocumento, int posicao) {
		return acessaDocumento(idDocumento).getRepresentacaoResumida(posicao);
	}
	
	/**
	 * Método que repassa a requisição de elevar um Elemento no armazenamento de Elementos
	 * do Documento requisitado
	 * @param idDocumento Id do Documento que terá o Elemento elevado
	 * @param posicao Posicao do Elemento que será elevado no armazenamento
	 */
	
	public void elevaElementoDocumento(String idDocumento, int posicao) {
		acessaDocumento(idDocumento).elevaElemento(posicao);
	}
	
	/**
	 * Método que repassa a requisição de ceder um Elemento no armazenamento de Elementos
	 * do Documento requisitado
	 * @param idDocumento Id do Documento que terá o Elemento cedido
	 * @param posicao Posicao do Elemento que será elevado no armazenamento
	 */
	
	public void cedeElementoDocumento(String idDocumento, int posicao) {
		acessaDocumento(idDocumento).cedeElemento(posicao);
	}
	
	/**
	 * Método que repassa a requisição de retornar um Elemento para o Documento
	 * @param idDocumento Id do Documento que terá o Elemento retornado
	 * @param posicao Posicao do Elemento que será retornado
	 * @return Elemento Elemento requisitado
	 */
	
	public Elemento getElementoDocumento(String idDocumento, int posicao) {
		return acessaDocumento(idDocumento).getElemento(posicao);
	}
	
	/**
	 * Método que repassa a requisição de retornar os Elementos de um Documento
	 * para a classe Documento
	 * @param idDocumento Id do Documento que terá os seus Elementos retornados
	 * @return ArrayList<Elemento> Array de Elementos do Documento requisitado
	 */
	
	public ArrayList<Elemento> getElementosDocumento(String idDocumento) {
		return acessaDocumento(idDocumento).getElementos();
	}
	
	/**
	 * Método que repassa a requisição de excluir um Elemento de um Documento para
	 * a classe Documento
	 * @param idDocumento Id do Documento que terá um Elemento excluído
	 * @param posicao Posição do Elemento que será excluído no armazenamento de 
	 * Elementos do Documento
	 * @return boolean Boolean indicando se houve a exclusão do Elemento ou não
	 */
	
	public boolean removeElementoDocumento(String idDocumento, int posicao) {
		return acessaDocumento(idDocumento).removeElemento(posicao);
	}
	
	private boolean composedOfSpaces(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ') {
				return false;
			}
		}
		return true;
	}
	
	private int getMediaPrioridades(ArrayList<Elemento> elementos) {
		int media = 0;
		
		for (Elemento elemento : elementos) {
			media += elemento.getPrioridade();
		}
		
		media = media != 0 ? media / elementos.size() : 0;
		
		return media;
	}
	
	private String criaRepresentacaoCompletaAtalho(ArrayList<Elemento> elementos) {
		String response = "";
		
		for (Elemento elemento : elementos) {
			if (elemento.getPrioridade() == 4 || elemento.getPrioridade() == 5) {
				response += elemento.exibirCompleto() + "\n";
			}
		}
		
		return response;
	}
	
	private String criaRepresentacaoResumidaAtalho(ArrayList<Elemento> elementos) {
		String response = "";
		
		for (Elemento elemento : elementos) {
			if (elemento.getPrioridade() == 4 || elemento.getPrioridade() == 5) {
				response += elemento.exibirResumido() + "\n";
			}
		}
		
		return response;
	}
}
