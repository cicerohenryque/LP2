package visao;

/**
 * Subclasse de visão que retorna a representação resumida dos elementos
 * do documento do tipo "Titulo" através do método "exibirVisao".
 */

import documento.Documento;
import elemento.Elemento;
import elemento.Titulo;
import java.util.ArrayList;

public class VisaoTitulo implements Visao {
	private Documento documento;
	
	/**
	 * Construtor de visaoTitulo que recebe o documento o qual terá seus
	 * elementos do tipo Titulo representados resumidamente pela visão
	 * @param documento Documento que terá seus dados utilizados
	 */
	
	public VisaoTitulo(Documento documento) {
		this.documento = documento;
	}
	
	/**
	 * Método que retorna a representação do Documento através de um array
	 * que possui a forma resumida de representar os elementos do tipo titulo 
	 * do documento
	 * @return response Array de String com a representação resumida dos
	 * elementos do tipo Titulo do Documento
	 */
	
	@Override
	public String[] exibirVisao() {
		ArrayList<Elemento> elementos = documento.getElementos();
		ArrayList<String> response = new ArrayList<>();
		
		for (Elemento elemento : elementos) {
			if (elemento.getClass() == Titulo.class) {
				response.add(elemento.exibirResumido());
			}
		}
		
		String[] finalResponse = response.toArray(new String[0]);
		
		return finalResponse;
	}
}
