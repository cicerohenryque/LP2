package documento;

import java.util.ArrayList;
import java.util.HashMap;

import elemento.Atalho;
import elemento.Elemento;
import elemento.Lista;
import elemento.Termos;
import elemento.Texto;
import elemento.Titulo;

/**
 * Classe que possui, retorna e manipula o armazenamento
 * de seus Elementos, sendo a classe que é requisitada
 * quando há uma operação sobre um Elemento no sistema
 */

public class Documento {
	private String titulo;
	private ArrayList<Elemento> elementos;
	private int tamanho;
	private boolean haveDoc;
	
	/**
	 * Construtor de Documento, que recebe o titulo e a
	 * quantidade máxima de elementos que o Documento
	 * pode possuir
	 * @param titulo Nome do Documento que será referenciado
	 * no sistema
	 * @param tamanho Tamanho máximo de armazenamento
	 * de Elementos no Documento
	 */
	
	public Documento(String titulo, int tamanho) {
		this.titulo = titulo;
		this.elementos = new ArrayList<Elemento>();
		this.tamanho = tamanho;
		this.haveDoc = false;
	}
	
	/**
	 * Construtor de Documento, que recebe somente o 
	 * titulo do Documento, deixando o tamanho como -1
	 * que será uma sinalização de quantidade infinita possível
	 * para o armazenamento de Elementos
	 * @param titulo Nome do Documento que será referenciado
	 * no sistema
	 */
	
	public Documento(String titulo) {
		this(titulo, -1);
	}
	
	/**
	 * Método que retorna a qtdeMaxima de Elementos
	 * que o Documento pode possuir
	 * @return int QtdeMaxima de Elementos que o 
	 * Documento pode possuir
	 */
	
	public int getTamanho() {
		return this.tamanho;
	}
	
	/**
	 * Método que retorna a quantidade de Elementos 
	 * cadastrados no Documento
	 * @return int Quantidade de Elementos
	 */

	public int getQtdeElementos() {
		return this.elementos.size();
	}
	
	/**
	 * Método que retornará a representação resumida
	 * de todos os Elementos presentes no Documento
	 * através de um array de Strings
	 * @return String[] Array de representações resumidas
	 * dos elementos do Documento
	 */
	
	public String[] getDocumento() {
		String[] stringsElementos = new String[elementos.size()];
		
		for (int i = 0; i < elementos.size(); i++) {
			stringsElementos[i] = elementos.get(i).exibirResumido();
		}
		
		return stringsElementos;
	}
	
	/**
	 * Método que adiciona um Texto no Documento, passando o valor
	 * do Texto e a sua prioridade
	 * @param valor Valor do Texto
	 * @param prioridade Prioridade do Texto
	 * @return int Posição do Elemento adicionado no seu armazenamento
	 */
	
	public int addTexto(String valor, int prioridade) {
		if (elementos.size() == tamanho) throw new IndexOutOfBoundsException("Tamanho limite atingido");
		
		Texto texto = new Texto(prioridade, valor);
		elementos.add(texto);
		
		return elementos.size();
	}
	
	/**
	 * Método que adiciona um Titulo no documento, passando o valor,
	 * a prioridade, o nivel e um booleano linkavel
	 * @param valor Valor do Titulo
	 * @param prioridade Prioridade do Titulo
	 * @param nivel Nivel do Titulo
	 * @param linkavel Booleano que indica se o Titulo é linkável ou não
	 * @return int Posição do Elemento adicionado no seu armazenamento
	 */
	
	public int addTitulo(String valor, int prioridade, int nivel, boolean linkavel) {
		if (nivel <= 0 || nivel > 5) throw new IllegalArgumentException("Nível inválido");
		if (elementos.size() == tamanho) throw new IndexOutOfBoundsException("Tamanho limite atingido");
		
		HashMap<String, String> propriedades = new HashMap<String, String>();
		propriedades.put("nivel", Integer.toString(nivel));
		propriedades.put("linkavel", Boolean.toString(linkavel));
		
		Titulo titulo = new Titulo(prioridade, valor, propriedades);
		elementos.add(titulo);
		
		return elementos.size();
	}
	
	/**
	 * Método que adiciona uma Lista no documento, passando o valor,
	 * a prioridade, o separador e o caracter de separação da Lista
	 * @param valor Valor do Titulo
	 * @param prioridade Prioridade do Titulo
	 * @param separador Caracter que indicará a separação do valor
	 * em pedaços
	 * @param charLista Caracter que servirá para a apresentação do 
	 * Elemento Lista
	 * @return int Posição do Elemento adicionado no seu armazenamento
	 */
	
	public int addLista(String valorLista, int prioridade, String separador, String charLista) {
		if (elementos.size() == tamanho) throw new IndexOutOfBoundsException("Tamanho limite atingido");
		
		HashMap<String, String> propriedades = new HashMap<String, String>();
		propriedades.put("separador", separador);
		propriedades.put("charLista", charLista);
		
		Lista lista = new Lista(prioridade, valorLista, propriedades);
		elementos.add(lista);
		
		return elementos.size();
	}
	
	/**
	 * Método que adiciona um Elemento Termos no documento, passando
	 * o valor, a prioridade, o separador e a ordem dos Termos
	 * @param valor Valor do Titulo
	 * @param prioridade Prioridade do Titulo
	 * @param separador Caracter que indicará a separação do valor
	 * em pedaços
	 * @param ordem Ordem em que os Termos irão ser representados
	 * podendo ser por ordem alfabética, de tamanho ou sem ordem
	 * @return int Posição do Elemento adicionado no seu armazenamento
	 */
	
	public int addTermos(String valorTermos, int prioridade, String separador, String ordem) {
		if (ordem != "NENHUM" && ordem != "ALFABETICA" && ordem != "TAMANHO") throw new IllegalArgumentException("Ordem não existe");
		if (elementos.size() == tamanho) throw new IndexOutOfBoundsException("Tamanho limite atingido");
		
		HashMap<String, String> propriedades = new HashMap<String, String>();
		propriedades.put("separador", separador);
		propriedades.put("ordem", ordem);
		
		Termos termo = new Termos(prioridade, valorTermos, propriedades);
		elementos.add(termo);
		
		return elementos.size();
	}

	/**
	 * Método que retornará uma String da representação completa
	 * de um Elemento
	 * @param posicao Posição do Elemento que a requisição pede
	 * para exibir 
	 * @return String Representação completa do Elemento 
	 */

	public String getRepresentacaoCompleta(int posicao) {
		return elementos.get(posicao - 1).exibirCompleto();
	}
	
	/**
	 * Método que retornará uma String da representação resumida
	 * de um Elemento
	 * @param posicao Posição do Elemento que a requisição pede
	 * para exibir 
	 * @return String Representação resumida do Elemento 
	 */
	
	public String getRepresentacaoResumida(int posicao) {
		return elementos.get(posicao - 1).exibirResumido();
	}
	
	/**
	 * Método que eleva um Elemento em sua posição no armazenamento
	 * de Elementos 
	 * @param posicao Posicao do Elemento que será elevado
	 */
	
	public void elevaElemento(int posicao) {
		if (posicao <= 0 || posicao > elementos.size()) throw new IndexOutOfBoundsException("Posição inválida");
		
		if (posicao != 1) {
			Elemento aux = elementos.get(posicao - 1);
			elementos.set(posicao - 1, elementos.get(posicao - 2));
			elementos.set(posicao - 2, aux);			
		}
	}
	
	/**
	 * Método que cede um Elemento em sua posição no armazenamento
	 * de Elementos 
	 * @param posicao Posicao do Elemento que será cedido
	 */
	
	public void cedeElemento(int posicao) {
		if (posicao <= 0 || posicao > elementos.size()) throw new IndexOutOfBoundsException("Posição inválida");
		
		if (posicao != elementos.size()) {
			Elemento aux = elementos.get(posicao);
			elementos.set(posicao, elementos.get(posicao - 1));
			elementos.set(posicao - 1, aux);			
		}
	}
	
	/**
	 * Método que retorna o Elemento na posição indicada
	 * @param posicao Posicao do Elemento que será retornado
	 * @return Elemento Elemento requisitado
	 */
	
	public Elemento getElemento(int posicao) {
		if (posicao <= 0 || posicao > elementos.size()) throw new IndexOutOfBoundsException("Posição inválida");
		
		return elementos.get(posicao - 1);
	}
	
	/**
	 * Método que retorna todos os Elementos do Documento
	 * @return ArrayList<Elemento> Elementos do Documento
	 * requisitado
	 */
	
	public ArrayList<Elemento> getElementos() {
		return elementos;
	}
	
	/**
	 * Método que remove o Elemento na posição indicada e
	 * da um Shift nos seus sucessores para que não haja
	 * nenhum vazio entre os Elementos
	 * @param posicao Posicao do Elemento que será removido
	 * @return boolean Boolean indicando se houve a remoção
	 * do Elemento
	 */
	
	public boolean removeElemento(int posicao) {
		if (posicao <= 0 || posicao > elementos.size()) return false;
		
		elementos.remove(posicao - 1);

		return true;
	}
	
	/**
	 * Método que adiciona um atalho no Documento, verificando 
	 * antes se já um atalho nesse Documento e se a quantidade
	 * de Elementos total já foi atingida
	 * @param prioridade Prioridade do Atalho
	 * @param valor Valor do Atalho
	 * @param propriedades Propriedades do Atalho, possuindo 
	 * as representações completas e resumidas
	 * @return int Posicao do Elemento no armazenamento de Elementos
	 * no Documento
	 */
	
	public int addAtalho(int prioridade, String valor, HashMap<String, String> propriedades) {
		if (elementos.size() == tamanho) throw new IndexOutOfBoundsException("Tamanho limite atingido");
		
		haveDoc = true;
		
		Atalho atalho = new Atalho(prioridade, valor, propriedades);
		
		elementos.add(atalho);
		
		return elementos.size();
	}
	
	/**
	 * Método que retorna se já há ou não um atalho adicionado
	 * nos Elementos do Documento
	 * @return boolean Boolean que indica se há ou não um Atalho
	 * entre os Elementos do Documento
	 */
	
	public boolean haveDoc() {
		return haveDoc;
	}
	
	/**
	 * Método que retorna o título do Documento
	 * @return String Título do Documento
	 */
	
	public String getTitulo() {
		return titulo;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj.getClass() != this.getClass()) return false;
		
		Documento doc = (Documento) obj;
		
		return doc.getTitulo().equals(this.getTitulo());
	}

}