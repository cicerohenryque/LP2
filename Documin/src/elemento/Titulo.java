package elemento;

import java.util.HashMap;

/**
 * Subclasse de Elemento que herda os métodos e atributos de Elemento e recebe 
 * um nível e um booleano representando o linkavel através HashMap de propriedades,
 * esses sendo utilizados para a exibição resumida e completa do Titulo
 */

public class Titulo extends Elemento{
	
	/**
	 * Construtor de Titulo, que recebe prioridade, valor e as propriedades que
	 * possuem o nível e o booleano linkavel
	 * @param prioridade Prioridade de Titulo
	 * @param valor Valor que será apresentado na sua exibição
	 * @param propriedades Propriedades que possue nível e o booleano linkavel
	 */
	
	public Titulo(int prioridade, String valor, HashMap<String, String> propriedades){
		super(prioridade, valor, propriedades);
	}
	
	/**
	 * Método que retornar a exibição completa do Elemento Titulo que será representado
	 * com seu nível, seu valor e se o booleano linkavel for igual a true, retornará 
	 * também a representação do valor como um link
	 * @return String Uma String de representação completa de Titulo
	 */
	
	@Override
	public String exibirCompleto() {
		String response;
		if (super.getPropriedades().get("linkavel").equals("true")) {
			String link = linkaValor(super.getValor());
			
			response = super.getPropriedades().get("nivel") + ". " + super.getValor() + " -- " + link;
		}
		else {
			response = super.getPropriedades().get("nivel") + ". " + super.getValor();
		}
		
		return response;
	}
	
	/**
	 * Método que retornar a exibição resumida do Elemento Titulo que será representado
	 * com o nível e o valor padrões passados no construtor da classe
	 * @return String Uma String de representação resumida de Titulo
	 */
	
	@Override
	public String exibirResumido() {
		String response = super.getPropriedades().get("nivel") + ". " + super.getValor();
		return response;
	}
	
	private String linkaValor(String str) {
		String response = "";
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ') {
				response += str.charAt(i);
			}
		}
		
		response = response.toUpperCase();
		
		return response;
	}
}
