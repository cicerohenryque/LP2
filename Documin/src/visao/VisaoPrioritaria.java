package visao;

/**
 * Subclasse de visão que retorna a representação resumida dos elementos
 * do documento de prioridade maior ou igual a dela através do método 
 * "exibirVisao".
 */

import documento.Documento;
import elemento.Elemento;
import java.util.ArrayList;

public class VisaoPrioritaria implements Visao {
	private int prioridadeBase; 
	private Documento documento;
	
	/**
	 * Construtor de visaoPrioritaria que recebe a prioridade base e o documento
	 * que terá seus elementos com prioridade igual ou maior que a prioridade base 
	 * representados resumidamente pela visão
	 * @param documento Documento que terá seus dados utilizados
	 * @param prioridadeBase Inteiro que representa a prioridade minima que um elemento
	 * deve ter para ser representado nessa visão
	 */
	
	public VisaoPrioritaria(int prioridadeBase, Documento documento) {
		this.documento = documento;
		this.prioridadeBase = prioridadeBase;
	}
	
	/**
	 * Método que retorna a representação do Documento através de um array
	 * que possui a forma resumida de representar os elementos com base maior ou 
	 * igual a prioridade base desse documento
	 * @return response Array de String com a representação resumida dos
	 * elementos do Documento com prioridade maior ou igual a prioridade base
	 */
	
	@Override
	public String[] exibirVisao() {
		ArrayList<Elemento> elementos = documento.getElementos();
		ArrayList<String> response = new ArrayList<>();
		
		for (Elemento elemento : elementos) {
			if (elemento.getPrioridade() >= prioridadeBase) {
				response.add(elemento.exibirResumido());
			}
		}

		String[] finalResponse = response.toArray(new String[0]);
		
		return finalResponse;
	}
}
