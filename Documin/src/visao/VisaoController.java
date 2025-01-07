package visao;

import documento.DocumentoController;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Controller que gerencia as requisições do sistema para as operações de Visões, 
 * tratando funcionalidades de criação, armazenamento e de requisição de visões
 */

public class VisaoController {
	private List<Visao> visoes;
	
	/**
	 * Constructor de visaoController que não recebe nenhum parâmetro, somente inicia
	 * o armazenamento de visões através de um ArrayList
	 */
	
	public VisaoController() {
		visoes = new ArrayList<>();
	}
	
	/**
	 * Método que cria uma visão completa de um documento que será resgatado através dos
	 * parâmetros que são passados, que será seu título e o controller de Documento, que será
	 * utilizado para a função de receber um documento
	 * @param tituloDoc Titulo do documento que será passado para a criação da visão completa
	 * @param documentoController Controller dos Documentos para realizar o método de resgate
	 * do documento referenciado
	 * @return int Id e Posição das visões no ArrayList de visões
	 */
	
    public int criarVisaoCompleta(String tituloDoc, DocumentoController documentoController) {
    	VisaoCompleta visaoCompleta = new VisaoCompleta(documentoController.acessaDocumento(tituloDoc));
    	visoes.add(visaoCompleta);
    	
    	return visoes.size();
    }
    
    /**
	 * Método que cria uma visão resumida de um documento que será resgatado através dos
	 * parâmetros que são passados, que será seu título e o controller de Documento, que será
	 * utilizado para a função de receber um documento
	 * @param tituloDoc Titulo do documento que será passado para a criação da visão resumida
	 * @param documentoController Controller dos Documentos para realizar o método de resgate
	 * do documento referenciado
	 * @return int Id e Posição das visões no ArrayList de visões
	 */
    
    public int criarVisaoResumida(String tituloDoc, DocumentoController documentoController) {
    	VisaoResumida visaoResumida = new VisaoResumida(documentoController.acessaDocumento(tituloDoc));
    	visoes.add(visaoResumida);
    	
    	return visoes.size();    	
    }
    
    /**
	 * Método que cria uma visão prioritaria de um documento que será resgatado através dos
	 * parâmetros do seu título e do controller de Documento, que será utilizado para a função 
	 * de receber um documento, além de receber a prioridade que será utilizada como base para 
	 * definir quais elementos são ser representados na visão prioritaria
	 * @param tituloDoc Titulo do documento que será passado para a criação da visão prioritaria
	 * @param prioridade Prioridade base que será utilizada para definir que elementos serão 
	 * representados na visão
	 * @param documentoController Controller dos Documentos para realizar o método de resgate
	 * do documento referenciado
	 * @return int Id e Posição das visões no ArrayList de visões
	 */
    
    public int criarVisaoPrioritaria(String tituloDoc, int prioridade, DocumentoController documentoController) {
    	VisaoPrioritaria visaoPrioritaria = new VisaoPrioritaria(prioridade, documentoController.acessaDocumento(tituloDoc));
    	visoes.add(visaoPrioritaria);
    	
    	return visoes.size(); 
    }
    
    /**
	 * Método que cria uma visão titulo de um documento que será resgatado através dos
	 * parâmetros que são passados, que será seu título e o controller de Documento, que será
	 * utilizado para a função de receber um documento
	 * @param tituloDoc Titulo do documento que será passado para a criação da visão titulo
	 * @param documentoController Controller dos Documentos para realizar o método de resgate
	 * do documento referenciado
	 * @return int Id e Posição das visões no ArrayList de visões
	 */
    
    public int criarVisaoTitulo(String tituloDoc, DocumentoController documentoController) {
    	VisaoTitulo visaoTitulo = new VisaoTitulo(documentoController.acessaDocumento(tituloDoc));
    	visoes.add(visaoTitulo);
    	
    	return visoes.size(); 
    }
    
    /**
     * Método que retornará o array de Strings com a representação definida pelo tipo de visão
     * armazenado na posição do ID passado como parâmetro no arrayList
     * @param visaoId Posição e Id da visão que a requisição pede para ser exibida
     * @return String[] Array de Strings com a representação definida pelo tipo de visão refenciada
     */
    
    public String[] exibirVisao(int visaoId) {
    	if (visaoId <= 0 || visaoId > visoes.size()) throw new IndexOutOfBoundsException("Id inválido");
    	
    	return visoes.get(visaoId-1).exibirVisao();
    }
}
