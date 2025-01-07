package visao;

/**
 * Interface que será superclasse dos outros tipos de visão,
 * possuindo o método comum a todos "exibirVisao" que serão
 * herdados pelas suas subclasses
 */

public interface Visao {
	
	/**
	 * Método comum à todos as subclassses de visão, mudando
	 * seu comportamento mas não sua assinatura
	 * @return array de String que possuirá a representação
	 * de elementos do Documento que cada visão terá
	 */
	
	public String[] exibirVisao();
}
