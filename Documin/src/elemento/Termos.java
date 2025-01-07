package elemento;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Subclasse de Elemento que herda os métodos e atributos de Elemento e recebe 
 * separador e ordem dos termos através do HashMap de propriedades, esses sendo 
 * utilizados para a exibição resumida e completa da Lista
 */

public class Termos extends Elemento{
	
	/**
	 * Construtor de Termos, que recebe prioridade, valor e as propriedades que
	 * possuem o separador e a ordem
	 * @param prioridade Prioridade de Termos
	 * @param valor Valor que será apresentado na sua exibição
	 * @param propriedades Propriedades que possue separador e a ordem
	 */
	
	public Termos(int prioridade, String valor, HashMap<String, String> propriedades) {
		super(prioridade, valor, propriedades);
	}
	
	/**
	 * Método que retornar a exibição completa do Elemento Termos que representará
	 * os valores separados através do separador e na ordem indicada nas propriedades
	 * podendo ser pela ordem alfabética, de tamanho ou por nenhuma ordem, além de possuir
	 * a indicação de quantos termos tem na lista
	 * @return String Uma String de representação completa de Termos
	 */
	
	@Override
	public String exibirCompleto() {
		int qtdeTermos = getQtdeTermos(super.getValor(), super.getPropriedades().get("separador"));
		String[] termos = splitedTermos(super.getValor(), super.getPropriedades().get("separador"), super.getPropriedades().get("ordem"));
		
		String response = "Total de Termos: " + qtdeTermos + "\n" + "- ";
		
		for (int i = 0; i < termos.length; i++) {
			response += termos[i];
			if (i != termos.length - 1) {
				response += ", ";
			}
		}
		
		return response;
	}
	
	/**
	 * Método que retornar a exibição resumida do Elemento Termos que representará
	 * o valor ordenado de acordo com a ordem passada no construtor, porém com os separadores
	 * na representação
	 * @return String Uma String de representação resumida de Termos
	 */
	
	@Override
	public String exibirResumido() {
		String[] termos = splitedTermos(super.getValor(), super.getPropriedades().get("separador"), super.getPropriedades().get("ordem"));
		String response = "";

		for (int i = 0; i < termos.length; i++) {
			response += termos[i];
			if (i != termos.length - 1) {
				response += " " + super.getPropriedades().get("separador") + " ";
			}
		}
		
		return response;
	}
	
	private int getQtdeTermos(String str, String separador) {
		int qtdeTermos = str.split(separador).length;
		
		return qtdeTermos;
	}
	
	private String[] splitedTermos(String str, String separador, String ordem) {
		String[] termos = str.split(separador);
		
		switch (ordem) {
		case "ALFABETICA": {
			Arrays.sort(termos);
		}
		case "TAMANHO": {
			Arrays.sort(termos, (a, b) -> Integer.compare(b.length(), a.length()));
		}
		}
	
		return termos;
	}
}