package elemento;

import java.util.HashMap;

/**
 * Superclasse abstrata que herda os métodos da interface Exibicionista, possuindo
 * as funcionalidades de se exibir completamente ou resumidamente. Essa superclasse
 * terá métodos e atributos que serão herdados pelas suas subclasses, possuindo 
 * prioridade, valor e propriedades. Essas subclasses serão armazenadas em documentos,
 * tendo uma relação de composição
 */

public abstract class Elemento implements Exibicionista{
	private int prioridade;
	private String valor;
	private HashMap<String, String> propriedades;
	
	/**
	 * Construtor de Elemento, que possúi atributos como prioridade, valor e propriedades
	 * que serão passados como parâmetro e armazenado em cada elemento criado
	 * @param prioridade Prioridade que o Elemento terá, sendo utilizado em métodos de 
	 * Documento
	 * @param valor Valor que o Elemento terá e representará nas suas exibições 
	 * @param propriedades Propriedades que o Elemento possuirá, que será um HashMap de 
	 * Strings para Strings representando particularidades de cada tipo de Elemento 
	 */
	
	public Elemento(int prioridade, String valor, HashMap<String, String> propriedades) {
		this.prioridade = prioridade;
		this.valor = valor;
		this.propriedades = propriedades;
	}
	
	/**
	 * Método que retornará o valor da superclasse Elemento
	 * @return String Valor do Elemento
	 */
	
	public String getValor() {
		return valor;
	}
	
	/**
	 * Método que retornará as propriedades da superclasse Elemento
	 * @return HashMap<String, String> Propriedades do Elemento
	 */
	
	public HashMap<String, String> getPropriedades() {
		return propriedades;
	}
	
	/**
	 * Método que retornará a prioridade da superclasse Elemento
	 * @return int Prioridade do Elemento
	 */
	
	public int getPrioridade() {
		return prioridade;
	}
}
