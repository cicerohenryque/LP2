package elemento;

import java.util.HashMap;

/**
 * Subclasse de Elemento que herda os métodos e atributos de Elemento e recebe 
 * separador e caractere de Lista através do HashMap de propriedades, esses sendo 
 * utilizados para a exibição resumida e completa da Lista
 */

public class Lista extends Elemento{
	
	/**
	 * Construtor de Lista, que recebe prioridade, valor e as propriedades que
	 * possuem o separador e o caractere de Lista
	 * @param prioridade Prioridade da Lista
	 * @param valor Valor que será apresentado na sua exibição
	 * @param propriedades Propriedades que possue separador e o caractere de lista
	 */
	
	public Lista(int prioridade, String valor, HashMap<String, String> propriedades) {
		super(prioridade, valor, propriedades);
	}
	
	/**
	 * Método que retornar a exibição completa do Elemento Lista representando
	 * o valor, separado através do separador, por linhas e com o carectere de 
	 * Lista antecedendo cada parte fracionada
	 * @return String Uma String de representação completa de Lista
	 */
	
	@Override
	public String exibirCompleto() {
		String response = preparaCompleto(super.getValor());
		return response;
	}
	
	/**
	 * Método que retornar a exibição resumida do Elemento Lista representando
	 * o valor sua forma padrão recebida no construtor
	 * @return String Uma String de representação resumida de Lista
	 */
	
	@Override
	public String exibirResumido() {
		String response = super.getValor();
		return response;
	}
	
	private String preparaCompleto(String str) {
		String parts[] = str.split(super.getPropriedades().get("separador"));
		
		String splited = "";
		
		for (int i = 0; i < parts.length; i++) {
			splited += super.getPropriedades().get("charLista") + " " + parts[i];
			if (i != parts.length - 1) {
				 splited += "\n";
			}
		}
		
		return splited;
	}
}
