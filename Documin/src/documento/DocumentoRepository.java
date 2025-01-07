package documento;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Classe que servirá de armazenamento de Documentos, retornando, excluindo
 * e adicionando Documentos no HashMap 
 */

public class DocumentoRepository {
	private HashMap<String, Documento> documentos;
	
	/**
	 * Construtor de DocumentoRespository que apenas iniciará o HashMap de 
	 * Documentos
	 */
	
	public DocumentoRepository() {
		this.documentos = new HashMap<String, Documento>();
	}
	
	/**
	 * Método que adicionará um Documento ao armazenamento passando seu titulo
	 * @param titulo Titulo do Documento que será adicionado
	 * @return boolean Boolean indicando se o Documento foi adicionado
	 */
	
	public boolean add(String titulo) {
		if (documentos.containsKey(titulo)) return false;
		
		Documento doc = new Documento(titulo);
		documentos.put(titulo, doc);
		return true;
	}

	/**
	 * Método que adicionará um Documento ao armazenamento passando seu titulo
	 * e seu tamanho maximo de Elementos suportados
	 * @param titulo Titulo do Documento que será adicionado
	 * @param tamanhoMaximo Tamanho maximo de Elementos suportados
	 * @return boolean Boolean indicando se o Documento foi adicionado
	 */
	
	public boolean add(String titulo, int tamanhoMaximo) {
		if (documentos.containsKey(titulo)) return false;
		
		Documento doc = new Documento(titulo, tamanhoMaximo);
		documentos.put(titulo, doc);
		return true;
	}
	
	/**
	 * Método que retornará um Documento do armazenamento acessado por seu titulo
	 * @param titulo Titulo do Documento que será retornado
	 * @return Documetno Documento requisitado
	 */
	
	public Documento get(String titulo) {
		if (!documentos.containsKey(titulo)) throw new NoSuchElementException("Elemento não encontrado");
		
		return documentos.get(titulo);
	}
	
	/**
	 * Método que removerá um Documento do armazenamento através de seu titulo
	 * @param titulo Titulo do Documento que será excluído
	 */
	
	public void remove(String titulo) {
		if (!documentos.containsKey(titulo)) throw new NoSuchElementException("Elemento não encontrado");
		
		documentos.remove(titulo);
	}
}
