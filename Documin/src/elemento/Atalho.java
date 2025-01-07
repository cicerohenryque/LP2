package elemento;

import java.util.HashMap;

/**
 * Subclasse de Elemento que herda os métodos e atributos de Elemento e representa um
 * documento como elemento de outro documento, possuindo as propriedades representação
 * completa e representação resumida em seu HashMap de String para String "propriedades"
 * e tendo suas exibições baseadas nos elementos do Documento referenciado pelo atalho
 */

public class Atalho extends Elemento{
	
	/**
	 * Construtor de Atalho, que recebe prioridade, valor e as propriedades que
	 * possuem a representação completa e resumida do Atalho
	 * @param prioridade Prioridade da Lista
	 * @param valor Valor que será apresentado na sua exibição
	 * @param propriedades Propriedades que possuem a representação completa e resumida do Atalho
	 */
	
	public Atalho(int prioridade, String valor, HashMap<String, String> propriedades) {
		super(prioridade, valor, propriedades);
	}
	
	/**
	 * Método que retornar a exibição completa dos Elementos que estavam no Documento
	 * referenciado pelo atalho
	 * @return String Uma String de representação completa de Atalho
	 */

	@Override
	public String exibirCompleto() {
		return getPropriedades().get("representacaoCompleta");
	}
	
	/**
	 * Método que retornar a exibição resumida dos Elementos que estavam no Documento
	 * referenciado pelo atalho
	 * @return String Uma String de representação completa de Atalho
	 */
	
	@Override
	public String exibirResumido() {
		return getPropriedades().get("representacaoResumido");
	}
}
