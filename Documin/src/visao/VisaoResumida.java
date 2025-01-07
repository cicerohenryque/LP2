package visao;

/**
 * Subclasse de visão que retorna a representação resumida dos elementos
 * de documento através do método "exibirVisao".
 */

import documento.Documento;
import elemento.Elemento;
import java.util.ArrayList;

public class VisaoResumida implements Visao {
	private Documento documento;
	
	/**
	 * Construtor de visaoResumida que recebe o documento o qual terá seus
	 * elementos representados resumidamente pela visão
	 * @param documento Documento que terá seus dados utilizados
	 */
	
	public VisaoResumida(Documento documento) {
		this.documento = documento;
	}
	
	/**
	 * Método que retorna a representação do Documento através de um array
	 * que possui a forma resumida de representar os elementos desse documento
	 * @return response Array de String com a representação resumida dos
	 * elementos do Documento
	 */
	
	@Override
	public String[] exibirVisao() {
		ArrayList<Elemento> elementos = documento.getElementos();
		String[] response = new String[elementos.size()];
		
		for (int i = 0; i < response.length; i++) {
			response[i] = elementos.get(i).exibirResumido();
		}
		
		return response;
	}
}
