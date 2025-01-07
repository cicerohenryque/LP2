package elemento;

/**
 * Subclasse de Elemento que herda os métodos e atributos de Elemento e recebe 
 * nenhuma propriedade através do HashMap de propriedades, logo não é passado
 * em seu construtor
 */

public class Texto extends Elemento{	
	
	/**
	 * Construtor de Texto, que recebe prioridade e o valor do Texto
	 * @param prioridade Prioridade do Texto
	 * @param valor Valor que será apresentado na sua exibição
	 */
	
	public Texto(int prioridade, String valor){
		super(prioridade, valor, null);
	}
	
	/**
	 * Método que retornar a exibição completa do Elemento Texto que é
	 * igual a versão resumida, ou seja, somente o valor na forma passada
	 * no construtor
	 * @return String Uma String de representação completa de Texto
	 */
	
	@Override
	public String exibirCompleto() {
		return super.getValor();
	}

	/**
	 * Método que retornar a exibição resumida do Elemento Texto que é
	 * igual a versão completa, ou seja, somente o valor na forma passada
	 * no construtor
	 * @return String Uma String de representação resumida de Texto
	 */
	
	@Override
	public String exibirResumido() {
		return super.getValor();
	}
}
