package elemento;

/**
 * Interface que terá a assinatura dos métodos exibirCompleto e exibirResumido
 * que serão sobreescritos nas suas subclasses que herdarão essa assinatura
 */

public interface Exibicionista {
	
	/**
	 * Método comum a todas as subclasses que tem sua assinatura herdada
	 * exibindo a subclasse de maneira completa
	 * @return String representação completa da subClasse
	 */
	
	abstract public String exibirCompleto();
	
	/**
	 * Método comum a todas as subclasses que tem sua assinatura herdada
	 * exibindo a subclasse de maneira resumida
	 * @return String representação resumida da subClasse
	 */
	
	abstract public String exibirResumido();
}
